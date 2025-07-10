package com.psicoclinic.psicoapp.clinic.infrastructure.entities;

import com.psicoclinic.psicoapp.clinic.application.enums.CareType;
import com.psicoclinic.psicoapp.clinic.application.enums.ClinicStatus;
import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clinic_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @OneToOne
  @JoinColumn(name = "psychologist_id", nullable = false, unique = true)
  private PsychologistEntity psychologistEntity;

  private String clinicName;

  @Enumerated(EnumType.STRING)
  private CareType careType;

  @Enumerated(EnumType.STRING)
  private ClinicStatus clinicStatus;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal sessionPrice;

  @ManyToMany
  @JoinTable(
      name = "clinic_profile_specialty",
      joinColumns = @JoinColumn(name = "clinic_profile_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id")
  )
  private List<SpecialtyEntity> specialties;
}

