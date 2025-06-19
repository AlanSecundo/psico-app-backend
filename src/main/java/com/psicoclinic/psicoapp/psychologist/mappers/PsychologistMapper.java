package com.psicoclinic.psicoapp.psychologist.mappers;

import com.psicoclinic.psicoapp.psychologist.dtos.PreOnboardingPsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.entities.Psychologist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PsychologistMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "crp", ignore = true)
  @Mapping(target = "onBoardCompleted", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "editedAt", ignore = true)
  @Mapping(target = "phone", ignore = true)
  Psychologist toEntity(PreOnboardingPsychologistDTO dto);

  PsychologistDTO toDTO(Psychologist entity);
}
