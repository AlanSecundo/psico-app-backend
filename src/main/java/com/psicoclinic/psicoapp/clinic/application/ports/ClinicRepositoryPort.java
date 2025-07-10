package com.psicoclinic.psicoapp.clinic.application.ports;

import com.psicoclinic.psicoapp.clinic.infrastructure.entities.ClinicEntity;

public interface ClinicRepositoryPort {
  void save(ClinicEntity clinic);
}
