package com.psicoclinic.psicoapp.psychologist.application.mappers;

import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.application.dtos.PsychologistDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PsychologistDTOMapper {

  PsychologistDTO toDTO(Psychologist psychologist);
}
