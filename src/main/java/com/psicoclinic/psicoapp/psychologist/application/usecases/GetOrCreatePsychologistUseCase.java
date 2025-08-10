package com.psicoclinic.psicoapp.psychologist.application.usecases;

import com.psicoclinic.psicoapp.psychologist.application.mappers.PsychologistDTOMapper;
import com.psicoclinic.psicoapp.psychologist.application.ports.PsychologistRepositoryPort;
import com.psicoclinic.psicoapp.psychologist.domain.model.Psychologist;
import com.psicoclinic.psicoapp.psychologist.application.dtos.PsychologistDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrCreatePsychologistUseCase {

  private final PsychologistRepositoryPort psychologistRepository;
  private final PsychologistDTOMapper mapper;

  public PsychologistDTO execute(String externalId, String name, String email) {
    return psychologistRepository.findByExternalId(externalId)
        .map(mapper::toDTO)
        .orElseGet(() -> {
          Psychologist newPsychologist = Psychologist.newRegister(externalId, name, email);

          newPsychologist = psychologistRepository.save(newPsychologist);

          return mapper.toDTO(newPsychologist);
        });
  }
}
