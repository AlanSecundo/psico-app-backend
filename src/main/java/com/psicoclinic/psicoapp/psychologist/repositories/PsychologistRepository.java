package com.psicoclinic.psicoapp.psychologist.repositories;


import com.psicoclinic.psicoapp.psychologist.entities.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PsychologistRepository extends JpaRepository<Psychologist, UUID> {

  Optional<Psychologist> findByExternalId(String externalId);
}

