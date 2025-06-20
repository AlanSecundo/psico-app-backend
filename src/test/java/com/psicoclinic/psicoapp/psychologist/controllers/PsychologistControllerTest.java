package com.psicoclinic.psicoapp.psychologist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psicoclinic.psicoapp.psychologist.dtos.PsychologistDTO;
import com.psicoclinic.psicoapp.psychologist.usecases.GetOrCreatePsychologistUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
// Potentially also: org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration if db related issues persist in WebMvcTest
// Potentially also: org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
// Potentially also: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(
    controllers = PsychologistController.class,
    excludeAutoConfiguration = {
        // Re-including SecurityAutoConfiguration and OAuth2ResourceServerAutoConfiguration
        // org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        // org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration.class
    }
)
class PsychologistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetOrCreatePsychologistUseCase getOrCreatePsychologistUseCase;

    @Autowired
    private ObjectMapper objectMapper; // Autowired for potential JSON conversion if needed, though JsonPath is primary

    @Test
    void testGetUserInfo() throws Exception {
        // Given
        String externalId = UUID.randomUUID().toString();
        String email = "test.user@example.com";
        String name = "Test User";

        // Mock Jwt principal
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", externalId);
        claims.put("email", email);
        claims.put("name", name);
        // Add any other claims your application might expect e.g. scope
        claims.put("scope", "openid profile email");


        Jwt mockJwt = new Jwt("mock-token-value",
                Instant.now(),
                Instant.now().plusSeconds(3600),
                Collections.singletonMap("alg", "none"), // Header
                claims); // Claims

        UUID psychologistUuid = UUID.randomUUID(); // This will be the ID in the DTO
        PsychologistDTO psychologistDTO = new PsychologistDTO(
                psychologistUuid,
                name,
                email,
                "1230984567", // phone
                "CRPTest/001", // crp
                true // onBoardCompleted
        );

        when(getOrCreatePsychologistUseCase.execute(externalId, name, email)).thenReturn(psychologistDTO);

        // When/Then
        mockMvc.perform(get("/psychologists/me")
                        .with(jwt().jwt(mockJwt).authorities(new SimpleGrantedAuthority("SCOPE_profile")))) // Added .authorities()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(psychologistUuid.toString()))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.phone").value("1230984567"))
                .andExpect(jsonPath("$.crp").value("CRPTest/001"))
                .andExpect(jsonPath("$.onBoardCompleted").value(true));

        // Verify use case was called
        verify(getOrCreatePsychologistUseCase).execute(externalId, name, email);
    }
}
