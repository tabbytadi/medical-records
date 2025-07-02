package com.example.medicalrecords.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExaminationDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime examinationDate;
    private Long diagnosisId;
    private String notes;
}