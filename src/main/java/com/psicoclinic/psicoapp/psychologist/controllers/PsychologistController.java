package com.psicoclinic.psicoapp.psychologist.controllers;

import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.usecases.GetOrCreatePsychologistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final GetOrCreatePsychologistUseCase getOrCreateUseCase;

    @GetMapping("/me")
    public ResponseEntity<PsychologistDTO> getUserInfo(@AuthenticationPrincipal Jwt principal) {
        String externalId = principal.getSubject();
        String email = principal.getClaimAsString("email");
        String name = principal.getClaimAsString("name");

        PsychologistDTO psychologist = getOrCreateUseCase.execute(externalId, name, email);

        return ResponseEntity.ok(psychologist);
    }
}
