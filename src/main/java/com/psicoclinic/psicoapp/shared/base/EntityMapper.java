package com.psicoclinic.psicoapp.shared.base;

public interface EntityMapper<Domain, Entity> {
  Entity toEntity(Domain domain);
  Domain toModel(Entity entity);
}
