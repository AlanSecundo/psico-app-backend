package com.psicoclinic.psicoapp.psychologist.infrastructure.mappers;

import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import com.psicoclinic.psicoapp.shared.base.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PsychologistEntityMapper extends EntityMapper<Psychologist, PsychologistEntity> {

  @Override
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "editedAt", ignore = true)
  PsychologistEntity toEntity(Psychologist model);
}
