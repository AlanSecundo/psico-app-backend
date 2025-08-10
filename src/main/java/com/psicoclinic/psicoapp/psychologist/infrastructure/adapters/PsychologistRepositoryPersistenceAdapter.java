package com.psicoclinic.psicoapp.psychologist.infrastructure.adapters;

import com.psicoclinic.psicoapp.psychologist.application.ports.PsychologistRepositoryPort;
import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import com.psicoclinic.psicoapp.psychologist.infrastructure.mappers.PsychologistEntityMapper;
import com.psicoclinic.psicoapp.psychologist.infrastructure.repositories.PsychologistJPARepository;
import com.psicoclinic.psicoapp.shared.base.PersistenceAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PsychologistRepositoryPersistenceAdapter
    extends PersistenceAdapter<Psychologist, PsychologistEntity, PsychologistJPARepository>
    implements PsychologistRepositoryPort {

  public PsychologistRepositoryPersistenceAdapter(PsychologistEntityMapper mapper, PsychologistJPARepository repository) {
    super(mapper, repository);
  }

  @Override
  public Optional<Psychologist> findByExternalId(String externalId) {
    return repository.findByExternalId(externalId).map(mapper::toModel);
  }
}
