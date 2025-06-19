package com.psicoclinic.psicoapp.psychologist.services;

import com.psicoclinic.psicoapp.psychologist.dtos.PreOnboardingPsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.entities.Psychologist;
import com.psicoclinic.psicoapp.psychologist.mappers.PsychologistMapper;
import com.psicoclinic.psicoapp.psychologist.repositories.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PsychologistService {

  private final PsychologistRepository repository;
  private final PsychologistMapper mapper;

  public Optional<PsychologistDTO> findByExternalId(String keycloakId) {
    return repository.findByExternalId(keycloakId).map(mapper::toDTO);
  }

  public PsychologistDTO create(PreOnboardingPsychologistDTO partialPsychologist) {
    Psychologist psychologist = mapper.toEntity(partialPsychologist);
    return mapper.toDTO(
        repository.save(psychologist)
    );
  }
}

