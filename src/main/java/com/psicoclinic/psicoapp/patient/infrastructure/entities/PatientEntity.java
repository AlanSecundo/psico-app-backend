package com.psicoclinic.psicoapp.patient.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "clinic_profile_id", nullable = false)
  private UUID clinicProfileId;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column
  private String gender;

  @Column
  private String email;

  @Column
  private String phone;

  @Column(columnDefinition = "TEXT")
  private String address;

  @Column(name = "has_representative", nullable = false)
  @Builder.Default
  private boolean hasRepresentative = false;

  @Column(name = "is_profile_complete", nullable = false)
  @Builder.Default
  private boolean isProfileComplete = false;

  @Column(name = "created_at", nullable = false)
  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
