package com.example.medicalrecords.web.view.controller.model;

import com.example.medicalrecords.web.view.controller.model.DoctorViewModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientViewModel {
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "EGN cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "EGN must be 10 digits")
    private String egn;

    private Boolean hasInsurance;

    private Long gpDoctorId;

    private DoctorViewModel gpDoctor;
}