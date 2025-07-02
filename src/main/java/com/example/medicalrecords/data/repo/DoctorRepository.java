package com.example.medicalrecords.data.repo;

import com.example.medicalrecords.data.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findByIsGp(Boolean isGp);
}