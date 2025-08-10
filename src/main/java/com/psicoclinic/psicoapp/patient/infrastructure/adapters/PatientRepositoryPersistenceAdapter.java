package com.psicoclinic.psicoapp.patient.infrastructure.adapters;

import com.psicoclinic.psicoapp.patient.application.ports.PatientRepositoryPort;
import com.psicoclinic.psicoapp.patient.domain.model.Patient;
import com.psicoclinic.psicoapp.patient.infrastructure.entities.PatientEntity;
import com.psicoclinic.psicoapp.patient.infrastructure.mappers.PatientEntityMapper;
import com.psicoclinic.psicoapp.patient.infrastructure.repositories.PatientJPARepository;
import com.psicoclinic.psicoapp.shared.base.PersistenceAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PatientRepositoryPersistenceAdapter
    extends PersistenceAdapter<Patient, PatientEntity, PatientJPARepository>
    implements PatientRepositoryPort  {

  public PatientRepositoryPersistenceAdapter(PatientEntityMapper mapper, PatientJPARepository repository) {
    super(mapper, repository);
  }

  @Override
  public List<Patient> findAllPatientsByClinic(UUID clinicId) {
      return repository.findByclinicProfileId(clinicId).stream().map(mapper::toModel).toList();
  };
}
