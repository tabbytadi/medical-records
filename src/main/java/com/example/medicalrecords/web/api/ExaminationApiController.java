package com.example.medicalrecords.web.api;

import com.example.medicalrecords.data.entity.Examination;
import com.example.medicalrecords.dto.ExaminationDTO;
import com.example.medicalrecords.service.ExaminationService;
import com.example.medicalrecords.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/examinations")
@RequiredArgsConstructor
public class ExaminationApiController {
    private final ExaminationService examinationService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public List<ExaminationDTO> getAllExaminations() {
        return mapperUtil.mapList(examinationService.getAllExaminations(), ExaminationDTO.class);
    }

    @GetMapping("/{id}")
    public ExaminationDTO getExaminationById(@PathVariable Long id) {
        return mapperUtil.getModelMapper().map(examinationService.getExaminationById(id), ExaminationDTO.class);
    }

    @PostMapping
    public ExaminationDTO createExamination(@RequestBody ExaminationDTO examinationDTO) {
        Examination examination = mapperUtil.getModelMapper().map(examinationDTO, Examination.class);
        return mapperUtil.getModelMapper().map(examinationService.createExamination(examination), ExaminationDTO.class);
    }

    @PutMapping("/{id}")
    public ExaminationDTO updateExamination(@PathVariable Long id, @RequestBody ExaminationDTO examinationDTO) {
        Examination examination = mapperUtil.getModelMapper().map(examinationDTO, Examination.class);
        return mapperUtil.getModelMapper().map(examinationService.updateExamination(id, examination), ExaminationDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteExamination(@PathVariable Long id) {
        examinationService.deleteExamination(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<ExaminationDTO> getExaminationsByPatient(@PathVariable Long patientId) {
        return mapperUtil.mapList(examinationService.getExaminationsByPatient(patientId), ExaminationDTO.class);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<ExaminationDTO> getExaminationsByDoctor(@PathVariable Long doctorId) {
        return mapperUtil.mapList(examinationService.getExaminationsByDoctor(doctorId), ExaminationDTO.class);
    }

    @GetMapping("/between")
    public List<ExaminationDTO> getExaminationsBetweenDates(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return mapperUtil.mapList(examinationService.getExaminationsBetweenDates(start, end), ExaminationDTO.class);
    }
}