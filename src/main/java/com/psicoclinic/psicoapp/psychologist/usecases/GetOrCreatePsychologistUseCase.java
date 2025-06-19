package com.psicoclinic.psicoapp.psychologist.usecases;

import com.psicoclinic.psicoapp.psychologist.dtos.PreOnboardingPsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.services.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrCreatePsychologistUseCase {

  private final PsychologistService psychologistService;

  public PsychologistDTO execute(String externalId, String name, String email) {
    return psychologistService.findByExternalId(externalId)
        .orElseGet(() -> {
          PreOnboardingPsychologistDTO psychologistInitialData = new PreOnboardingPsychologistDTO(
              externalId,
              name,
              email
          );

          return psychologistService.create(psychologistInitialData);
        });
  }
}
