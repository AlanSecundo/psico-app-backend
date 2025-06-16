package com.saudeplu.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String services;

    @ManyToMany
    @JoinTable(
            name = "clinic_profile_specialty",
            joinColumns = @JoinColumn(name = "clinic_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();

    @OneToOne(mappedBy = "clinicProfile")
    private Psychologist psychologist;
}
