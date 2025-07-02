package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    private String medicineName;
    private String dosage;
    private Integer durationDays;
    private String instructions;
}