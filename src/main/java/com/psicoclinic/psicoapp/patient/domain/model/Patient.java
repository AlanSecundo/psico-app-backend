package com.psicoclinic.psicoapp.patient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Patient {

  private final UUID id;
  private final UUID clinicProfileId;
  private final String fullName;
  private final LocalDate birthDate;
  private final String cpf;
  private final String gender;
  private final String email;
  private final String phone;
  private final String address;
  private final boolean hasRepresentative;
  private final boolean isProfileComplete;
}
