package com.psicoclinic.psicoapp.patient.infrastructure.repositories;

import com.psicoclinic.psicoapp.patient.infrastructure.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientJPARepository extends JpaRepository<PatientEntity, UUID> {

  List<PatientEntity> findByclinicProfileId(UUID clinicProfileId);
}
