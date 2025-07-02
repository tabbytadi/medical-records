package com.example.medicalrecords.web.api;

import com.example.medicalrecords.data.entity.Doctor;
import com.example.medicalrecords.dto.DoctorDTO;
import com.example.medicalrecords.service.DoctorService;
import com.example.medicalrecords.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorApiController {
    private final DoctorService doctorService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return mapperUtil.mapList(doctorService.getAllDoctors(), DoctorDTO.class);
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return mapperUtil.getModelMapper().map(doctorService.getDoctorById(id), DoctorDTO.class);
    }

    @PostMapping
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = mapperUtil.getModelMapper().map(doctorDTO, Doctor.class);
        return mapperUtil.getModelMapper().map(doctorService.createDoctor(doctor), DoctorDTO.class);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = mapperUtil.getModelMapper().map(doctorDTO, Doctor.class);
        return mapperUtil.getModelMapper().map(doctorService.updateDoctor(id, doctor), DoctorDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    @GetMapping("/specialty/{specialty}")
    public List<DoctorDTO> getDoctorsBySpecialty(@PathVariable String specialty) {
        return mapperUtil.mapList(doctorService.getDoctorsBySpecialty(specialty), DoctorDTO.class);
    }

    @GetMapping("/gp")
    public List<DoctorDTO> getGpDoctors() {
        return mapperUtil.mapList(doctorService.getGpDoctors(), DoctorDTO.class);
    }
}