package com.example.medicalrecords.exceptions;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(Long id) {
        super("Doctor not found with id: " + id);
    }

    public DoctorNotFoundException(String licenseNumber) {
        super("Doctor not found with license number: " + licenseNumber);
    }
}