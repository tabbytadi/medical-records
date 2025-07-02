package com.example.medicalrecords.exceptions;

public class ExaminationNotFoundException extends RuntimeException {
    public ExaminationNotFoundException(Long id) {
        super("Examination not found with id: " + id);
    }
}