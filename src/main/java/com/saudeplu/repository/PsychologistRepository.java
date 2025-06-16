package com.saudeplu.repository;

import com.saudeplu.models.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, UUID> {
}
