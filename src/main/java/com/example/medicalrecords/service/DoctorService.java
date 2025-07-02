package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
    List<Doctor> getDoctorsBySpecialty(String specialty);
    List<Doctor> getGpDoctors();
}