package com.psicoclinic.psicoapp.psychologist.infrastructure.repositories;


import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PsychologistJPARepository extends JpaRepository<PsychologistEntity, UUID> {

  Optional<PsychologistEntity> findByExternalId(String externalId);
}

