package com.psicoclinic.psicoapp.patient.application.ports;

import com.psicoclinic.psicoapp.patient.domain.model.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientRepositoryPort {
  List<Patient> findAllPatientsByClinic(UUID clinicId);

  Patient save(Patient patient);
}
