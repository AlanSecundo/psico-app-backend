package com.psicoclinic.psicoapp.patient.application.mapper;

import com.psicoclinic.psicoapp.patient.application.dto.PatientDTO;
import com.psicoclinic.psicoapp.patient.domain.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientDTOMapper {

  PatientDTO toDTO(Patient patient);
}
