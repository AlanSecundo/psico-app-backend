package com.saudeplu.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clinic_profiles")
public class ClinicProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String complement;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zipCode;

    @Column(columnDefinition = "TEXT")
    private String services; // This field was present in the original file, keeping it as per instructions to only add if not existent.

    @ManyToMany
    @JoinTable(
            name = "clinic_profile_specialty",
            joinColumns = @JoinColumn(name = "clinic_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();

    @OneToOne(mappedBy = "clinicProfile")
    private Psychologist psychologist;

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }
}
