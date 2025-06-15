package com.psicoclinic.psicoapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping; // Added
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psicoclinic.psicoapp.dto.UserDTO; // Added import

@RestController
@RequestMapping("")
public class UserController {

    @GetMapping("/me")
    public UserDTO getUserInfo(@AuthenticationPrincipal Jwt principal) {
        String id = principal.getSubject();
        String email = principal.getClaimAsString("email");
        String name = principal.getClaimAsString("name");

        return new UserDTO(id, email, name);
    }
}
