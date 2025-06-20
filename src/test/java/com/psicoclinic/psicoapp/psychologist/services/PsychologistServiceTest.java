package com.psicoclinic.psicoapp.psychologist.services;

import com.psicoclinic.psicoapp.psychologist.entities.Psychologist;
import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.mappers.PsychologistMapper;
import com.psicoclinic.psicoapp.psychologist.repositories.PsychologistRepository;
import com.psicoclinic.psicoapp.psychologist.dtos.PreOnboardingPsychologistDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PsychologistServiceTest {

    @Mock
    private PsychologistRepository psychologistRepository;

    @Mock
    private PsychologistMapper psychologistMapper;

    @InjectMocks
    private PsychologistService psychologistService;

    @Test
    void testFindByExternalId_whenPsychologistExists() {
        // Given
        String psychologistId = UUID.randomUUID().toString();
        String stringPsychologistIdFromService = UUID.randomUUID().toString(); // This is the externalId used in service method
        UUID psychologistUuidForDTO = UUID.randomUUID(); // This will be the ID field in the DTO

        Psychologist psychologistEntity = new Psychologist();
        // psychologistEntity.setId(1L); // Internal DB ID
        psychologistEntity.setExternalId(stringPsychologistIdFromService); // String externalId
        psychologistEntity.setName("Dr. Jane Doe");
        psychologistEntity.setEmail("jane.doe@example.com");
        // psychologistEntity.setUuid(psychologistUuidForDTO); // If entity also has UUID

        PsychologistDTO psychologistDTO = new PsychologistDTO(
                psychologistUuidForDTO,
                "Dr. Jane Doe",
                "jane.doe@example.com",
                "1234567890", // phone
                "CRP00/12345", // crp
                true // onBoardCompleted
        );

        when(psychologistRepository.findByExternalId(stringPsychologistIdFromService)).thenReturn(Optional.of(psychologistEntity));
        when(psychologistMapper.toDTO(psychologistEntity)).thenReturn(psychologistDTO);

        // When
        Optional<PsychologistDTO> result = psychologistService.findByExternalId(stringPsychologistIdFromService);

        // Then
        verify(psychologistRepository).findByExternalId(stringPsychologistIdFromService);
        verify(psychologistMapper).toDTO(psychologistEntity);
        assertTrue(result.isPresent());
        assertEquals(psychologistDTO, result.get());
    }

    @Test
    void testFindByExternalId_whenPsychologistDoesNotExist() {
        // Given
        String psychologistId = UUID.randomUUID().toString();
        when(psychologistRepository.findByExternalId(psychologistId)).thenReturn(Optional.empty());

        // When
        Optional<PsychologistDTO> result = psychologistService.findByExternalId(psychologistId);

        // Then
        verify(psychologistRepository).findByExternalId(psychologistId);
        verify(psychologistMapper, never()).toDTO(any(com.psicoclinic.psicoapp.psychologist.entities.Psychologist.class));
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreate() {
        // Given
        String preOnboardingExternalId = UUID.randomUUID().toString();
        PreOnboardingPsychologistDTO preOnboardingPsychologistDTO = new PreOnboardingPsychologistDTO(
                preOnboardingExternalId,
                "Dr. John Smith",
                "john.smith@example.com"
        );

        Psychologist mappedPsychologistEntity = new Psychologist();
        mappedPsychologistEntity.setExternalId(preOnboardingExternalId); // Assuming entity has this setter
        mappedPsychologistEntity.setName("Dr. John Smith");
        mappedPsychologistEntity.setEmail("john.smith@example.com");

        Psychologist savedPsychologistEntity = new Psychologist();
        // savedPsychologistEntity.setId(2L); // Internal DB ID
        savedPsychologistEntity.setExternalId(preOnboardingExternalId); // Assuming entity has this
        savedPsychologistEntity.setName("Dr. John Smith");
        savedPsychologistEntity.setEmail("john.smith@example.com");
        // If saved entity also gets a UUID, let's assume one for DTO mapping
        UUID savedEntityUuid = UUID.randomUUID();
        // savedPsychologistEntity.setUuid(savedEntityUuid); // If applicable

        PsychologistDTO resultPsychologistDTO = new PsychologistDTO(
                savedEntityUuid, // This would be mapped from savedPsychologistEntity
                "Dr. John Smith",
                "john.smith@example.com",
                "0987654321", // phone
                "CRP00/54321", // crp
                false // onBoardCompleted
        );

        when(psychologistMapper.toEntity(preOnboardingPsychologistDTO)).thenReturn(mappedPsychologistEntity);
        when(psychologistRepository.save(mappedPsychologistEntity)).thenReturn(savedPsychologistEntity);
        when(psychologistMapper.toDTO(savedPsychologistEntity)).thenReturn(resultPsychologistDTO);

        // When
        PsychologistDTO result = psychologistService.create(preOnboardingPsychologistDTO);

        // Then
        verify(psychologistMapper).toEntity(preOnboardingPsychologistDTO);
        verify(psychologistRepository).save(mappedPsychologistEntity);
        verify(psychologistMapper).toDTO(savedPsychologistEntity);
        assertEquals(resultPsychologistDTO, result);
    }
}
