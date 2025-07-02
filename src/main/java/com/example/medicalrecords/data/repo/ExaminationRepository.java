package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    List<Examination> findByPatientId(Long patientId);
    List<Examination> findByDoctorId(Long doctorId);
    List<Examination> findByExaminationDateBetween(LocalDateTime start, LocalDateTime end);
}