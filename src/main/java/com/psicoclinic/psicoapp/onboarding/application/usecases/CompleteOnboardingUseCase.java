package com.psicoclinic.psicoapp.onboarding.application.usecases;

import com.psicoclinic.psicoapp.clinic.application.ports.ClinicRepositoryPort;
import com.psicoclinic.psicoapp.clinic.application.ports.SpecialtyRepositoryPort;
import com.psicoclinic.psicoapp.clinic.infrastructure.entities.ClinicEntity;
import com.psicoclinic.psicoapp.clinic.infrastructure.entities.SpecialtyEntity;
import com.psicoclinic.psicoapp.onboarding.application.dtos.CompleteOnboardingDTO;
import com.psicoclinic.psicoapp.psychologist.application.ports.PsychologistRepositoryPort;
import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.infrastructure.mappers.PsychologistEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

// ⚠️ Architecture note: direct use of JPA Entity in this UseCase is a conscious decision.
// See ADR-007: we use the entity directly as Specialty/ClinicProfile currently have no business logic.
// If these entities evolve, they should be refactored into domain models with proper mapping.

@RequiredArgsConstructor
@Component
public class CompleteOnboardingUseCase {

  private final PsychologistRepositoryPort psychologistRepository;
  private final ClinicRepositoryPort clinicRepository;
  private final SpecialtyRepositoryPort specialtyRepository;
  private final PsychologistEntityMapper psychologistEntityMapper;

  public void execute(String externalId, CompleteOnboardingDTO onboardingInformation) {
    Psychologist psychologist = psychologistRepository.findByExternalId(externalId)
        .orElseThrow(() -> new RuntimeException("Psychologist not found"));

    if (psychologist.isOnBoardCompleted()) {
      throw new IllegalStateException("Onboarding already completed");
    }

    List<SpecialtyEntity> specialties = specialtyRepository.findAllById(onboardingInformation.specialtiesIds());

    ClinicEntity clinic = new ClinicEntity();
    clinic.setPsychologistEntity(psychologistEntityMapper.toEntity(psychologist));
    clinic.setSpecialties(specialties);
    clinic.setClinicName(onboardingInformation.clinicName());
    clinic.setClinicStatus(onboardingInformation.clinicStatus());
    clinic.setCareType(onboardingInformation.careType());
    clinic.setSessionPrice(onboardingInformation.sessionPrice());

    psychologist = psychologist.completeOnboard(onboardingInformation.crp(), onboardingInformation.phone());

    clinicRepository.save(clinic);
    psychologistRepository.save(psychologist);
  }
}
