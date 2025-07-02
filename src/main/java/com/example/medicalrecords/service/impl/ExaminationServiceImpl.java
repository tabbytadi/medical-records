package com.example.medicalrecords.service.impl;

import com.example.medicalrecords.data.entity.Examination;
import com.example.medicalrecords.data.repo.ExaminationRepository;
import com.example.medicalrecords.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {
    private final ExaminationRepository examinationRepository;

    @Override
    public List<Examination> getAllExaminations() {
        return examinationRepository.findAll();
    }

    @Override
    public Examination getExaminationById(Long id) {
        return examinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + id));
    }

    @Override
    public Examination createExamination(Examination examination) {
        return examinationRepository.save(examination);
    }

    @Override
    public Examination updateExamination(Long id, Examination examination) {
        Examination existing = getExaminationById(id);
        existing.setPatient(examination.getPatient());
        existing.setDoctor(examination.getDoctor());
        existing.setExaminationDate(examination.getExaminationDate());
        existing.setDiagnosis(examination.getDiagnosis());
        existing.setNotes(examination.getNotes());
        return examinationRepository.save(existing);
    }

    @Override
    public void deleteExamination(Long id) {
        examinationRepository.deleteById(id);
    }

    @Override
    public List<Examination> getExaminationsByPatient(Long patientId) {
        return examinationRepository.findByPatientId(patientId);
    }

    @Override
    public List<Examination> getExaminationsByDoctor(Long doctorId) {
        return examinationRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Examination> getExaminationsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return examinationRepository.findByExaminationDateBetween(start, end);
    }
}