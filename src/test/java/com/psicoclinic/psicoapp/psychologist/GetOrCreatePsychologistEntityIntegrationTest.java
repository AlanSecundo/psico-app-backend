package com.psicoclinic.psicoapp.psychologist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetOrCreatePsychologistEntityIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private JwtRequestPostProcessor buildJwt() {
    return jwt()
        .jwt(jwt -> jwt
            .subject("external-id-123")
            .claim("email", "john@example.com")
            .claim("name", "John Doe"));
  }

  @Test
  void shouldCreateAndReturnPsychologistFromJwt() throws Exception {
    mockMvc.perform(get("/psychologists/me")
            .with(buildJwt())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.email").value("john@example.com"))
        .andExpect(jsonPath("$.name").value("John Doe"));
  }
}
