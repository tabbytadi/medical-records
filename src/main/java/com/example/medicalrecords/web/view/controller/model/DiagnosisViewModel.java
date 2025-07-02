package com.example.medicalrecords.web.view.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisViewModel {
    private Long id;

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;
}