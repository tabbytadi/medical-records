package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.Diagnosis;
import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> getAllDiagnosis();
    Diagnosis getDiagnosisById(Long id);
    Diagnosis createDiagnosis(Diagnosis diagnosis);
    Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis);
    void deleteDiagnosis(Long id);
    Diagnosis getDiagnosisByCode(String code);
    List<Diagnosis> searchDiagnosis(String query);
}