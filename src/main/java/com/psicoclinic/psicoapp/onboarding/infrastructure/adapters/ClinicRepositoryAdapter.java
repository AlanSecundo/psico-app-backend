package com.psicoclinic.psicoapp.onboarding.infrastructure.adapters;

import com.psicoclinic.psicoapp.clinic.application.ports.ClinicRepositoryPort;
import com.psicoclinic.psicoapp.clinic.infrastructure.entities.ClinicEntity;
import com.psicoclinic.psicoapp.onboarding.infrastructure.repositories.ClinicJPARepository;
import com.psicoclinic.psicoapp.psychologist.infrastructure.repositories.PsychologistJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicRepositoryAdapter implements ClinicRepositoryPort {

  private final ClinicJPARepository repository;

  @Override
  public void save(ClinicEntity clinic) {
    repository.save(clinic);
  }
}
