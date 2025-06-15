package com.psicoclinic.psicoapp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String keycloakId;
    private String email;
    private String name;
    private Boolean onBoardCompleted;

    public UserDTO(String id, String email, String name) {
        this.keycloakId = id;
        this.email = email;
        this.name = name;
    }
}
