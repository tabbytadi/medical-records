package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String egn;

    private Boolean hasInsurance;

    @ManyToOne
    @JoinColumn(name = "gp_doctor_id")
    private Doctor gpDoctor;

    @OneToMany(mappedBy = "patient")
    private Set<Examination> examinations;
}