package com.psicoclinic.psicoapp.psychologist.infrastructure.adapters;

import com.psicoclinic.psicoapp.psychologist.application.ports.PsychologistRepositoryPort;
import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import com.psicoclinic.psicoapp.psychologist.infrastructure.mappers.PsychologistEntityMapper;
import com.psicoclinic.psicoapp.psychologist.infrastructure.repositories.PsychologistJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PsychologistRepositoryPersistenceAdapter implements PsychologistRepositoryPort {

  private final PsychologistJPARepository repository;
  private final PsychologistEntityMapper mapper;

  @Override
  public Optional<Psychologist> findByExternalId(String externalId) {
    return repository.findByExternalId(externalId)
        .map(mapper::toModel);
  }

  @Override
  public Psychologist save(Psychologist psychologist) {
    PsychologistEntity newEntity = mapper.toEntity(psychologist);
    newEntity = repository.save(newEntity);
    return mapper.toModel(newEntity);
  }
}
