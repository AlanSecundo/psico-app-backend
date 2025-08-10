package com.psicoclinic.psicoapp.patient.infrastructure.controllers;

import com.psicoclinic.psicoapp.patient.application.dto.PatientDTO;
import com.psicoclinic.psicoapp.patient.application.dto.PatientRequestDTO;
import com.psicoclinic.psicoapp.patient.application.usecases.CreatePatientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

  private final CreatePatientUseCase createPatientUseCase;

  @PostMapping
  public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientRequestDTO patientRequest) {
    PatientDTO patientDTO = createPatientUseCase.execute(patientRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO);
  }
}
