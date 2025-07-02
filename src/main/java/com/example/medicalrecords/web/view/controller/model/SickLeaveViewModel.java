package com.example.medicalrecords.web.view.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SickLeaveViewModel {
    private Long id;

    @NotNull(message = "Examination is required")
    private Long examinationId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Duration days is required")
    private Integer durationDays;
}