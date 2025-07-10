package com.psicoclinic.psicoapp.onboarding.infrastructure.controllers;

import com.psicoclinic.psicoapp.onboarding.application.dtos.CompleteOnboardingDTO;
import com.psicoclinic.psicoapp.onboarding.application.usecases.CompleteOnboardingUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboard")
@RequiredArgsConstructor
public class OnboardingController {

  private final CompleteOnboardingUseCase completeOnboardingUseCase;

  @PostMapping
  public ResponseEntity<Void> completeOnboarding(
      @AuthenticationPrincipal Jwt jwt,
      @RequestBody @Valid CompleteOnboardingDTO dto
  ) {
    String externalId = jwt.getSubject();
    completeOnboardingUseCase.execute(externalId, dto);
    return ResponseEntity.noContent().build();
  }
}

