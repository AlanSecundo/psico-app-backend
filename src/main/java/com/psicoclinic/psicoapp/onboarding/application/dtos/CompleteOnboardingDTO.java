package com.psicoclinic.psicoapp.onboarding.application.dtos;

import com.psicoclinic.psicoapp.clinic.application.enums.CareType;
import com.psicoclinic.psicoapp.clinic.application.enums.ClinicStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CompleteOnboardingDTO(
    String crp,
    String phone,
    List<UUID> specialtiesIds,
    String clinicName,
    CareType careType,
    ClinicStatus clinicStatus,
    BigDecimal sessionPrice
) {}

