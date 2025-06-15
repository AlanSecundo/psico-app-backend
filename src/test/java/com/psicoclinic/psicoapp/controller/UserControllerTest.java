package com.psicoclinic.psicoapp.controller;

import com.psicoclinic.psicoapp.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUserInfoWhenAuthenticated() throws Exception {
        // 1. Mock Jwt and its claims
        Jwt mockJwt = mock(Jwt.class);
        when(mockJwt.getSubject()).thenReturn("testUserId");
        when(mockJwt.getClaimAsString("email")).thenReturn("test@example.com");
        when(mockJwt.getClaimAsString("name")).thenReturn("Test User");

        // 2. Perform GET request to /me with the mocked Jwt principal
        mockMvc.perform(get("/me")
                        .with(jwt().jwt(mockJwt))) // Use jwt() post-processor
                // 3. Verify HTTP 200 OK status
                .andExpect(status().isOk())
                // 4. Verify response body
                .andExpect(jsonPath("$.id").value("testUserId"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.name").value("Test User"));
    }
}
