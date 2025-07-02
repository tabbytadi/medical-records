package com.example.medicalrecords.service;

import com.example.medicalrecords.data.entity.Patient;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    List<Patient> getPatientsByGpDoctor(Long doctorId);
    List<Patient> getPatientsWithInsurance();
}