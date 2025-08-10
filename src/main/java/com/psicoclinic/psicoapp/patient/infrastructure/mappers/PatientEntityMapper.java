package com.psicoclinic.psicoapp.patient.infrastructure.mappers;

import com.psicoclinic.psicoapp.patient.domain.model.Patient;
import com.psicoclinic.psicoapp.patient.infrastructure.entities.PatientEntity;
import com.psicoclinic.psicoapp.shared.base.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper extends EntityMapper<Patient, PatientEntity> {
}
