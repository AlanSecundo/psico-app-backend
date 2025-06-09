package com.example.psicoclinic.psicoapp.service;

import com.example.psicoclinic.psicoapp.entity.Psychologist;
import com.example.psicoclinic.psicoapp.repository.PsychologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;

    @Autowired
    public PsychologistService(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }

    public Psychologist findByKeycloakId(String keycloakId) {
        return psychologistRepository.findByKeycloakId(keycloakId).orElse(null);
        // Consider throwing a custom exception if not found, depending on requirements
    }

    // You can add other service methods here as needed
    // For example, a method to create or update a psychologist
    public Psychologist save(Psychologist psychologist) {
        return psychologistRepository.save(psychologist);
    }
}
