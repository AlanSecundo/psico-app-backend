package com.psicoclinic.psicoapp.onboarding.infrastructure.repositories;

import com.psicoclinic.psicoapp.clinic.infrastructure.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialtyJPARepository extends JpaRepository<SpecialtyEntity, UUID> {
}
