package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}