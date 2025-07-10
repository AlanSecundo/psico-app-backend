package com.psicoclinic.psicoapp.clinic.application.ports;

import com.psicoclinic.psicoapp.clinic.infrastructure.entities.SpecialtyEntity;

import java.util.List;
import java.util.UUID;

public interface SpecialtyRepositoryPort {
  List<SpecialtyEntity> findAllById(List<UUID> ids);
}
