package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.Diagnosis;
import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> getAllDiagnoses();
    Diagnosis getDiagnosisById(Long id);
    Diagnosis createDiagnosis(Diagnosis diagnosis);
    Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis);
    void deleteDiagnosis(Long id);
    Diagnosis getDiagnosisByCode(String code);
    List<Diagnosis> searchDiagnoses(String query);
}