package com.psicoclinic.psicoapp.psychologist.usecases;

import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.dtos.PreOnboardingPsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.services.PsychologistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOrCreatePsychologistUseCaseTest {

    @Mock
    private PsychologistService psychologistService;

    @InjectMocks
    private GetOrCreatePsychologistUseCase getOrCreatePsychologistUseCase;

    @Test
    void testExecute_whenPsychologistExists() {
        // Given
        String externalId = UUID.randomUUID().toString();
        String name = "Dr. Alice Wonderland";
        String email = "alice.wonderland@example.com";
        UUID existingPsychologistUuid = UUID.randomUUID();

        PsychologistDTO existingPsychologistDTO = new PsychologistDTO(
                existingPsychologistUuid,
                name,
                email,
                "111222333", // phone
                "CRP01/111", // crp
                true // onBoardCompleted
        );

        when(psychologistService.findByExternalId(externalId)).thenReturn(Optional.of(existingPsychologistDTO));

        // When
        PsychologistDTO result = getOrCreatePsychologistUseCase.execute(externalId, name, email);

        // Then
        verify(psychologistService).findByExternalId(externalId);
        verify(psychologistService, never()).create(any(PreOnboardingPsychologistDTO.class));
        assertEquals(existingPsychologistDTO, result);
    }

    @Test
    void testExecute_whenPsychologistDoesNotExist() {
        // Given
        String externalId = UUID.randomUUID().toString();
        String name = "Dr. Bob The Builder";
        String email = "bob.builder@example.com";
        UUID newlyCreatedPsychologistUuid = UUID.randomUUID();

        PsychologistDTO newlyCreatedPsychologistDTO = new PsychologistDTO(
                newlyCreatedPsychologistUuid,
                name,
                email,
                "444555666", // phone
                "CRP01/222", // crp
                false // onBoardCompleted
        );

        when(psychologistService.findByExternalId(externalId)).thenReturn(Optional.empty());
        when(psychologistService.create(any(PreOnboardingPsychologistDTO.class))).thenReturn(newlyCreatedPsychologistDTO);

        ArgumentCaptor<PreOnboardingPsychologistDTO> preOnboardingDTOCaptor = ArgumentCaptor.forClass(PreOnboardingPsychologistDTO.class);

        // When
        PsychologistDTO result = getOrCreatePsychologistUseCase.execute(externalId, name, email);

        // Then
        verify(psychologistService).findByExternalId(externalId);
        verify(psychologistService).create(preOnboardingDTOCaptor.capture());

        PreOnboardingPsychologistDTO capturedDTO = preOnboardingDTOCaptor.getValue();
        assertEquals(externalId, capturedDTO.externalId());
        assertEquals(name, capturedDTO.name());
        assertEquals(email, capturedDTO.email());

        assertEquals(newlyCreatedPsychologistDTO, result);
    }
}
