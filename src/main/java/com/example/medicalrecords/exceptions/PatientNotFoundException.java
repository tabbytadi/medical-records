package com.example.medicalrecords.exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(Long id) {
        super("Patient not found with id: " + id);
    }

    public PatientNotFoundException(String egn) {
        super("Patient not found with EGN: " + egn);
    }
}