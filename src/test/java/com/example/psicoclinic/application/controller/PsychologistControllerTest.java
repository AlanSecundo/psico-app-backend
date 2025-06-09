package com.example.psicoclinic.application.controller;

import com.example.psicoclinic.domain.entity.Psychologist;
import com.example.psicoclinic.domain.service.PsychologistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PsychologistControllerTest {

    @Mock
    private PsychologistService psychologistService;

    @InjectMocks
    private PsychologistController psychologistController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Psychologist psychologist;
    private UUID psychologistId;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(psychologistController).build();
        objectMapper = new ObjectMapper();
        psychologistId = UUID.randomUUID();
        psychologist = new Psychologist(psychologistId, "Dr. Test", "test@example.com", "password", "CRP123");
    }

    @Test
    void getAllPsychologists_success() throws Exception {
        when(psychologistService.getAllPsychologists()).thenReturn(List.of(psychologist));

        mockMvc.perform(get("/psychologists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dr. Test"));
    }

    @Test
    void getAllPsychologists_emptyList() throws Exception {
        when(psychologistService.getAllPsychologists()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/psychologists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getPsychologistById_success() throws Exception {
        when(psychologistService.getPsychologistById(psychologistId)).thenReturn(Optional.of(psychologist));

        mockMvc.perform(get("/psychologists/{id}", psychologistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(psychologistId.toString()))
                .andExpect(jsonPath("$.name").value("Dr. Test"));
    }

    @Test
    void getPsychologistById_notFound() throws Exception {
        when(psychologistService.getPsychologistById(psychologistId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/psychologists/{id}", psychologistId))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePsychologist_success() throws Exception {
        Psychologist updatedDetails = new Psychologist(psychologistId, "Dr. Updated Test", "updated@example.com", "newpass", "CRP456");
        when(psychologistService.updatePsychologist(eq(psychologistId), any(Psychologist.class))).thenReturn(updatedDetails);

        mockMvc.perform(put("/psychologists/{id}", psychologistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Updated Test"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void updatePsychologist_notFound() throws Exception {
        Psychologist updatedDetails = new Psychologist(psychologistId, "Dr. Updated Test", "updated@example.com", "newpass", "CRP456");
        when(psychologistService.updatePsychologist(eq(psychologistId), any(Psychologist.class)))
                .thenThrow(new EntityNotFoundException("Psychologist not found"));

        mockMvc.perform(put("/psychologists/{id}", psychologistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isNotFound());
    }
}
