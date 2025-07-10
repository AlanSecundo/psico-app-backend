package com.psicoclinic.psicoapp.psychologist.infrastructure.mappers;

import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.infrastructure.entities.PsychologistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PsychologistEntityMapper {

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "editedAt", ignore = true)
  PsychologistEntity toEntity(Psychologist model);

  Psychologist toModel(PsychologistEntity entity);
}
