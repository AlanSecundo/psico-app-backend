package com.psicoclinic.psicoapp.dto;

public class UserDTO {
    private String id;
    private String email;
    private String name;

    public UserDTO(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // Setters (optional, depending on use case)
    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
