package com.example.psicoclinic.domain.service;

import com.example.psicoclinic.domain.entity.Psychologist;
import com.example.psicoclinic.domain.repository.PsychologistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PsychologistServiceTest {

    @Mock
    private PsychologistRepository psychologistRepository;

    @InjectMocks
    private PsychologistService psychologistService;

    private Psychologist psychologist;
    private UUID psychologistId;

    @BeforeEach
    void setUp() {
        psychologistId = UUID.randomUUID();
        psychologist = new Psychologist(psychologistId, "John Doe", "john.doe@example.com", "password123", "CRP001");
    }

    @Test
    void createPsychologist_emailExists_throwsIllegalArgumentException() {
        Psychologist existingPsychologist = new Psychologist(UUID.randomUUID(), "Jane Doe", "john.doe@example.com", "pass", "CRP002");
        when(psychologistRepository.findAll()).thenReturn(List.of(existingPsychologist));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            psychologistService.createPsychologist(psychologist);
        });

        assertEquals("Já existe um psicólogo cadastrado com este email.", exception.getMessage());
        verify(psychologistRepository, times(1)).findAll();
        verify(psychologistRepository, never()).save(any(Psychologist.class));
    }

    @Test
    void getAllPsychologists_returnsList() {
        when(psychologistRepository.findAll()).thenReturn(List.of(psychologist));

        List<Psychologist> psychologists = psychologistService.getAllPsychologists();

        assertFalse(psychologists.isEmpty());
        assertEquals(1, psychologists.size());
        verify(psychologistRepository, times(1)).findAll();
    }

    @Test
    void getAllPsychologists_returnsEmptyList() {
        when(psychologistRepository.findAll()).thenReturn(Collections.emptyList());

        List<Psychologist> psychologists = psychologistService.getAllPsychologists();

        assertTrue(psychologists.isEmpty());
        verify(psychologistRepository, times(1)).findAll();
    }

    @Test
    void getPsychologistById_found() {
        when(psychologistRepository.findById(psychologistId)).thenReturn(Optional.of(psychologist));

        Optional<Psychologist> found = psychologistService.getPsychologistById(psychologistId);

        assertTrue(found.isPresent());
        assertEquals(psychologist, found.get());
        verify(psychologistRepository, times(1)).findById(psychologistId);
    }

    @Test
    void getPsychologistById_notFound() {
        when(psychologistRepository.findById(psychologistId)).thenReturn(Optional.empty());

        Optional<Psychologist> found = psychologistService.getPsychologistById(psychologistId);

        assertTrue(found.isEmpty());
        verify(psychologistRepository, times(1)).findById(psychologistId);
    }

    @Test
    void updatePsychologist_success() {
        Psychologist updatedDetails = new Psychologist(psychologistId, "John Updated", "john.updated@example.com", "newpass", "CRP002");
        when(psychologistRepository.findById(psychologistId)).thenReturn(Optional.of(psychologist));
        when(psychologistRepository.save(any(Psychologist.class))).thenReturn(updatedDetails);

        Psychologist updated = psychologistService.updatePsychologist(psychologistId, updatedDetails);

        assertNotNull(updated);
        assertEquals("John Updated", updated.getName());
        assertEquals("john.updated@example.com", updated.getEmail());
        verify(psychologistRepository, times(1)).findById(psychologistId);
        verify(psychologistRepository, times(1)).save(psychologist);
    }

    @Test
    void updatePsychologist_notFound_throwsEntityNotFoundException() {
        Psychologist updatedDetails = new Psychologist(psychologistId, "John Updated", "john.updated@example.com", "newpass", "CRP002");
        when(psychologistRepository.findById(psychologistId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            psychologistService.updatePsychologist(psychologistId, updatedDetails);
        });

        assertEquals("Psicólogo não encontrado com o ID: " + psychologistId, exception.getMessage());
        verify(psychologistRepository, times(1)).findById(psychologistId);
        verify(psychologistRepository, never()).save(any(Psychologist.class));
    }

    @Test
    void deletePsychologist_success() {
        when(psychologistRepository.existsById(psychologistId)).thenReturn(true);
        doNothing().when(psychologistRepository).deleteById(psychologistId);

        psychologistService.deletePsychologist(psychologistId);

        verify(psychologistRepository, times(1)).existsById(psychologistId);
        verify(psychologistRepository, times(1)).deleteById(psychologistId);
    }

    @Test
    void deletePsychologist_notFound_throwsEntityNotFoundException() {
        when(psychologistRepository.existsById(psychologistId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            psychologistService.deletePsychologist(psychologistId);
        });

        assertEquals("Psicólogo não encontrado com o ID: " + psychologistId, exception.getMessage());
        verify(psychologistRepository, times(1)).existsById(psychologistId);
        verify(psychologistRepository, never()).deleteById(psychologistId);
    }
}
