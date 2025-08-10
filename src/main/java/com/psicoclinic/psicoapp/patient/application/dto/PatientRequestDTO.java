package com.psicoclinic.psicoapp.patient.application.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record PatientRequestDTO(

    @NotNull(message = "Clinic ID is required")
    UUID clinicProfileId,

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 255)
    String fullName,

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    LocalDate birthDate,

    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
    String cpf,

    @NotBlank(message = "Gender is required")
    String gender,

    @Email(message = "Invalid email format")
    String email,

    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    String phone,

    String address,

    boolean hasRepresentative
) {}