package com.example.medicalrecords.exceptions;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String field, String value) {
        super("Duplicate entry for " + field + ": " + value);
    }
}