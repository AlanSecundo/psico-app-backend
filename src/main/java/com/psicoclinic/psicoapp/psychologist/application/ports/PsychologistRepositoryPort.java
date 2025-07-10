package com.psicoclinic.psicoapp.psychologist.application.ports;

import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;

import java.util.Optional;

public interface PsychologistRepositoryPort {
  Optional<Psychologist> findByExternalId(String externalId);
  Psychologist save(Psychologist psychologist);
}
