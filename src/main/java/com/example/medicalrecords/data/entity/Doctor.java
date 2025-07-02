package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor extends BaseEntity {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String licenseNumber;

    private String specialty;
    private Boolean isGp;

    @OneToMany(mappedBy = "gpDoctor")
    private Set<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private Set<Examination> examinations;
}