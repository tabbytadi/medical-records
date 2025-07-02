package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis extends BaseEntity {
    @Column(unique = true)
    private String code;

    private String name;
    private String description;

    @OneToMany(mappedBy = "diagnosis")
    private Set<Examination> examinations;
}