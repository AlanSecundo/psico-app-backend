package com.example.psicoclinic.application.controller;

import com.example.psicoclinic.domain.entity.Psychologist;
import com.example.psicoclinic.domain.service.PsychologistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService psychologistService;

    @GetMapping
    public List<Psychologist> getAllPsychologists() {
        return psychologistService.getAllPsychologists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psychologist> getPsychologistById(@PathVariable UUID id) {
        Optional<Psychologist> psychologist = psychologistService.getPsychologistById(id);
        return psychologist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psychologist> updatePsychologist(@PathVariable UUID id, @RequestBody Psychologist psychologistDetails) {
        try {
            Psychologist updatedPsychologist = psychologistService.updatePsychologist(id, psychologistDetails);
            return ResponseEntity.ok(updatedPsychologist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
