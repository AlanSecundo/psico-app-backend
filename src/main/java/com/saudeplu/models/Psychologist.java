package com.saudeplu.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "psychologists")
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String crp;

    @Column
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column
    private String profilePicture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_profile_id", referencedColumnName = "id")
    private ClinicProfile clinicProfile;

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ClinicProfile getClinicProfile() {
        return clinicProfile;
    }

    public void setClinicProfile(ClinicProfile clinicProfile) {
        this.clinicProfile = clinicProfile;
    }
}
