package com.psicoclinic.psicoapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping; // Added
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psicoclinic.psicoapp.dto.UserDTO; // Added import

@RestController
@RequestMapping("") // Changed from "/users" to "" to make the endpoint /me
public class UserController {

    @GetMapping("/me")
    public UserDTO getUserInfo(@AuthenticationPrincipal Jwt principal) {
        String id = principal.getSubject(); // 'sub' claim is typically used for user ID
        String email = principal.getClaimAsString("email");
        String name = principal.getClaimAsString("name"); // Or "preferred_username", "given_name", etc.

        return new UserDTO(id, email, name);
    }
}
