package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.Examination;
import java.time.LocalDateTime;
import java.util.List;

public interface ExaminationService {
    List<Examination> getAllExaminations();
    Examination getExaminationById(Long id);
    Examination createExamination(Examination examination);
    Examination updateExamination(Long id, Examination examination);
    void deleteExamination(Long id);
    List<Examination> getExaminationsByPatient(Long patientId);
    List<Examination> getExaminationsByDoctor(Long doctorId);
    List<Examination> getExaminationsBetweenDates(LocalDateTime start, LocalDateTime end);
}