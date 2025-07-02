package com.example.medicalrecords.exceptions;

public class DiagnosisNotFoundException extends RuntimeException {
    public DiagnosisNotFoundException(Long id) {
        super("Diagnosis not found with id: " + id);
    }

    public DiagnosisNotFoundException(String code) {
        super("Diagnosis not found with code: " + code);
    }
}