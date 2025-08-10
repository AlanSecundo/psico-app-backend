package com.psicoclinic.psicoapp.patient.application.usecases;

import com.psicoclinic.psicoapp.patient.application.dto.PatientDTO;
import com.psicoclinic.psicoapp.patient.application.dto.PatientRequestDTO;
import com.psicoclinic.psicoapp.patient.application.mapper.PatientDTOMapper;
import com.psicoclinic.psicoapp.patient.application.ports.PatientRepositoryPort;
import com.psicoclinic.psicoapp.patient.domain.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePatientUseCase {

  private final PatientRepositoryPort patientRepository;
  private final PatientDTOMapper mapper;

  public PatientDTO execute(PatientRequestDTO patient) {
    Patient newPatient = Patient.builder()
        .fullName(patient.fullName())
        .cpf(patient.cpf())
        .address(patient.address())
        .email(patient.email())
        .phone(patient.phone())
        .birthDate(patient.birthDate())
        .gender(patient.gender())
        .hasRepresentative(patient.hasRepresentative())
        .clinicProfileId(patient.clinicProfileId())
        .isProfileComplete(false)
        .build();

    newPatient = patientRepository.save(newPatient);

    return mapper.toDTO(newPatient);
  }
}
