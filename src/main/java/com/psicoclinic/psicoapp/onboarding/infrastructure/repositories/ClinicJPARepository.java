package com.psicoclinic.psicoapp.onboarding.infrastructure.repositories;

import com.psicoclinic.psicoapp.clinic.infrastructure.entities.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicJPARepository extends JpaRepository<ClinicEntity, UUID> {
}
