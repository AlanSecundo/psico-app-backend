package com.example.psicoclinic.psicoapp.controller;

import com.example.psicoclinic.psicoapp.entity.Psychologist;
import com.example.psicoclinic.psicoapp.service.PsychologistService; // Assuming you have a service layer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/psychologists") // Base path for psychologist related endpoints
public class PsychologistController {

    private final PsychologistService psychologistService;

    @Autowired
    public PsychologistController(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    @GetMapping("/me")
    public ResponseEntity<Psychologist> getAuthenticatedPsychologist(@AuthenticationPrincipal Jwt jwt) {
        // The 'sub' claim in the JWT usually holds the user's unique ID from Keycloak
        String keycloakId = jwt.getSubject();
        Psychologist psychologist = psychologistService.findByKeycloakId(keycloakId); // You'll need to implement this service method
        if (psychologist != null) {
            return ResponseEntity.ok(psychologist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
