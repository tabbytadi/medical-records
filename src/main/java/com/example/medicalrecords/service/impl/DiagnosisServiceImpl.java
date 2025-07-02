package com.example.medicalrecords.service.impl;

import com.example.medicalrecords.data.entity.Diagnosis;
import com.example.medicalrecords.data.repo.DiagnosisRepository;
import com.example.medicalrecords.exceptions.DiagnosisNotFoundException;
import com.example.medicalrecords.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis getDiagnosisById(Long id) {
        return diagnosisRepository.findById(id)
                .orElseThrow(() -> new DiagnosisNotFoundException(id));
    }

    @Override
    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis) {
        Diagnosis existing = getDiagnosisById(id);
        existing.setCode(diagnosis.getCode());
        existing.setName(diagnosis.getName());
        existing.setDescription(diagnosis.getDescription());
        return diagnosisRepository.save(existing);
    }

    @Override
    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }

    @Override
    public Diagnosis getDiagnosisByCode(String code) {
        return diagnosisRepository.findByCode(code)
                .orElseThrow(() -> new DiagnosisNotFoundException(code));
    }

    @Override
    public List<Diagnosis> searchDiagnosis(String query) {
        return diagnosisRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(query, query);
    }
}