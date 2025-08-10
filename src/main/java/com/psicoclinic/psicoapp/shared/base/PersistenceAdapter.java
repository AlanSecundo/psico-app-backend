package com.psicoclinic.psicoapp.shared.base;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@AllArgsConstructor
public abstract class PersistenceAdapter<Domain, Entity, Repository extends JpaRepository<Entity, UUID>> {

  protected final EntityMapper<Domain, Entity> mapper;
  protected final Repository repository;

  public Domain save(Domain domain) {
    Entity newEntity = mapper.toEntity(domain);
    newEntity = repository.save(newEntity);
    return mapper.toModel(newEntity);
  }
}
