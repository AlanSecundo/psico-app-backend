package com.psicoclinic.psicoapp.clinic.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "specialty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyEntity {

  @Id
  private UUID id;

  @Column(name = "name", unique = true, nullable = false, length = 100)
  private String name;

  @ManyToMany(mappedBy = "specialties")
  private Set<ClinicEntity> clinics;
}
