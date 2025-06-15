package com.psicoclinic.psicoapp.dto;

public class UserDTO {
    private String id;
    private String email;
    private String name;

    /**
     * Constructs a new UserDTO with the specified id, email, and name.
     *
     * @param id the unique identifier for the user
     * @param email the user's email address
     * @param name the user's name
     */
    public UserDTO(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    /****
     * Returns the user's unique identifier.
     *
     * @return the user ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the email address associated with this user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's name.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /****
     * Sets the user's unique identifier.
     *
     * @param id the new user ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /****
     * Sets the user's email address.
     *
     * @param email the new email address to assign to the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's name.
     *
     * @param name the new name to assign to the user
     */
    public void setName(String name) {
        this.name = name;
    }
}
