package com.example.medicalrecords.exceptions;

public class SickLeaveNotFoundException extends RuntimeException {
    public SickLeaveNotFoundException(Long id) {
        super("Sick leave not found with id: " + id);
    }
}