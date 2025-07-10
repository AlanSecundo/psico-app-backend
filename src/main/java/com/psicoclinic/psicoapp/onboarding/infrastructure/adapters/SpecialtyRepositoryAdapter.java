package com.psicoclinic.psicoapp.onboarding.infrastructure.adapters;

import com.psicoclinic.psicoapp.clinic.application.ports.SpecialtyRepositoryPort;
import com.psicoclinic.psicoapp.clinic.infrastructure.entities.SpecialtyEntity;
import com.psicoclinic.psicoapp.onboarding.infrastructure.repositories.SpecialtyJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SpecialtyRepositoryAdapter implements SpecialtyRepositoryPort {

  private final SpecialtyJPARepository repository;

  @Override
  public List<SpecialtyEntity> findAllById(List<UUID> ids) {
      return repository.findAllById(ids);
  }
}
