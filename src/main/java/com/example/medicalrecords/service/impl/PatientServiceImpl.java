package com.example.medicalrecords.service.impl;

import com.example.medicalrecords.data.entity.Patient;
import com.example.medicalrecords.data.repo.PatientRepository;
import com.example.medicalrecords.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Patient existing = getPatientById(id);
        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setEgn(patient.getEgn());
        existing.setHasInsurance(patient.getHasInsurance());
        existing.setGpDoctor(patient.getGpDoctor());
        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getPatientsByGpDoctor(Long doctorId) {
        return patientRepository.findByGpDoctorId(doctorId);
    }

    @Override
    public List<Patient> getPatientsWithInsurance() {
        return patientRepository.findByHasInsurance(true);
    }
}