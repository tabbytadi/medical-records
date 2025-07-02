package com.example.medicalrecords.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String egn;
    private Boolean hasInsurance;
    private Long gpDoctorId;
}