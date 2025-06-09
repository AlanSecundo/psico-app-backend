package com.example.psicoclinic.psicoapp.repository;

import com.example.psicoclinic.psicoapp.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // Import Optional
import java.util.UUID;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, UUID> {
    Optional<Psychologist> findByKeycloakId(String keycloakId); // New method
}
