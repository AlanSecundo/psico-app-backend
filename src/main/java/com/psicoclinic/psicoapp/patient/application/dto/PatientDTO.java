package com.psicoclinic.psicoapp.patient.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PatientDTO(
    UUID id,
    UUID clinicProfileId,
    String fullName,
    LocalDate birthDate,
    String cpf,
    String gender,
    String email,
    String phone,
    String address,
    boolean hasRepresentative,
    boolean isProfileComplete
) {}
