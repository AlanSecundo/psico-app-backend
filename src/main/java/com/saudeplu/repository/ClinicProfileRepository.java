package com.saudeplu.repository;

import com.saudeplu.models.ClinicProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClinicProfileRepository extends JpaRepository<ClinicProfile, UUID> {
}
