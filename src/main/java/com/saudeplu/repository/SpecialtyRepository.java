package com.saudeplu.repository;

import com.saudeplu.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {
}
