package com.example.medicalrecords.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String specialty;
    private Boolean isGp;
}