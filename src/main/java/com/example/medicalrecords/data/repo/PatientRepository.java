package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByGpDoctorId(Long doctorId);
    List<Patient> findByHasInsurance(Boolean hasInsurance);
}