package com.example.medicalrecords.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "examinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Examination extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime examinationDate;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    private String notes;

    @OneToOne(mappedBy = "examination", cascade = CascadeType.ALL)
    private Prescription prescription;

    @OneToOne(mappedBy = "examination", cascade = CascadeType.ALL)
    private SickLeave sickLeave;
}