package com.example.medicalrecords.web.view.controller.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SickLeaveViewModel {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int durationDays;
}