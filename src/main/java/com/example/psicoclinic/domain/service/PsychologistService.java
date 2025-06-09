package com.example.psicoclinic.domain.service;

import com.example.psicoclinic.domain.entity.Psychologist;
import com.example.psicoclinic.domain.repository.PsychologistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;

    public PsychologistService(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }

    public Psychologist createPsychologist(Psychologist psychologist) {
        // Verifica se já existe psicólogo com o mesmo email
        if (psychologistRepository.findAll().stream()
                .anyMatch(p -> p.getEmail().equals(psychologist.getEmail()))) {
            throw new IllegalArgumentException("Já existe um psicólogo cadastrado com este email.");
        }
        // Verifica se já existe psicólogo com o mesmo CRP
        if (psychologistRepository.findAll().stream()
                .anyMatch(p -> p.getCrp().equals(psychologist.getCrp()))) {
            throw new IllegalArgumentException("Já existe um psicólogo cadastrado com este CRP.");
        }
        return psychologistRepository.save(psychologist);
    }

    public List<Psychologist> getAllPsychologists() {
        return psychologistRepository.findAll();
    }

    public Optional<Psychologist> getPsychologistById(UUID id) {
        return psychologistRepository.findById(id);
    }

    public Psychologist updatePsychologist(UUID id, Psychologist psychologistDetails) {
        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Psicólogo não encontrado com o ID: " + id));

        psychologist.setName(psychologistDetails.getName());
        psychologist.setEmail(psychologistDetails.getEmail());
        psychologist.setPassword(psychologistDetails.getPassword());
        psychologist.setCrp(psychologistDetails.getCrp());

        return psychologistRepository.save(psychologist);
    }

    public void deletePsychologist(UUID id) {
        if (!psychologistRepository.existsById(id)) {
            throw new EntityNotFoundException("Psicólogo não encontrado com o ID: " + id);
        }
        psychologistRepository.deleteById(id);
    }
}
