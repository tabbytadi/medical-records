package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}