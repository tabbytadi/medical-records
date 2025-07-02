package com.example.medicalrecords.web.view.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExaminationViewModel {
    private Long id;

    @NotNull(message = "Patient is required")
    private Long patientId;
    private String patientName;

    @NotNull(message = "Doctor is required")
    private Long doctorId;
    private String doctorName;

    @NotNull(message = "Date is required")
    private LocalDateTime examinationDate;

    @NotNull(message = "Diagnosis is required")
    private Long diagnosisId;
    private String diagnosisName;

    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}