package com.example.medicalrecords.web.view.controller.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionViewModel {
    private Long id;
    private String medication;
    private String dosage;
    private String instructions;
}