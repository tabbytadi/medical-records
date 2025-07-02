package com.example.medicalrecords.web.api;

import com.example.medicalrecords.data.entity.Patient;
import com.example.medicalrecords.dto.PatientDTO;
import com.example.medicalrecords.service.PatientService;
import com.example.medicalrecords.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientApiController {
    private final PatientService patientService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return mapperUtil.mapList(patientService.getAllPatients(), PatientDTO.class);
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return mapperUtil.getModelMapper().map(patientService.getPatientById(id), PatientDTO.class);
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = mapperUtil.getModelMapper().map(patientDTO, Patient.class);
        return mapperUtil.getModelMapper().map(patientService.createPatient(patient), PatientDTO.class);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        Patient patient = mapperUtil.getModelMapper().map(patientDTO, Patient.class);
        return mapperUtil.getModelMapper().map(patientService.updatePatient(id, patient), PatientDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/gp/{doctorId}")
    public List<PatientDTO> getPatientsByGpDoctor(@PathVariable Long doctorId) {
        return mapperUtil.mapList(patientService.getPatientsByGpDoctor(doctorId), PatientDTO.class);
    }

    @GetMapping("/with-insurance")
    public List<PatientDTO> getPatientsWithInsurance() {
        return mapperUtil.mapList(patientService.getPatientsWithInsurance(), PatientDTO.class);
    }
}