package com.example.medicalrecords.web.view.controller;

import com.example.medicalrecords.data.entity.*;
import com.example.medicalrecords.service.*;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.view.controller.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/examinations")
@RequiredArgsConstructor
public class ExaminationViewController {
    private final ExaminationService examinationService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DiagnosisService diagnosisService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String getAllExaminations(Model model) {
        try {
            List<ExaminationViewModel> examinations = examinationService.getAllExaminations().stream()
                    .map(this::convertToViewModel)
                    .collect(Collectors.toList());
            model.addAttribute("examinations", examinations);
            return "examinations/examinations";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading examinations: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/create-examination")
    public String showCreateExaminationForm(Model model) {
        model.addAttribute("examination", new ExaminationViewModel());
        populateDropdowns(model);
        return "examinations/create-examination";
    }

    @PostMapping("/create")
    public String createExamination(@Valid @ModelAttribute("examination") ExaminationViewModel examinationViewModel,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateDropdowns(model);
            return "examinations/create-examination";
        }

        Examination examination = mapperUtil.getModelMapper().map(examinationViewModel, Examination.class);
        examination.setPatient(patientService.getPatientById(examinationViewModel.getPatientId()));
        examination.setDoctor(doctorService.getDoctorById(examinationViewModel.getDoctorId()));
        examination.setDiagnosis(diagnosisService.getDiagnosisById(examinationViewModel.getDiagnosisId()));

        examinationService.createExamination(examination);
        return "redirect:/examinations";
    }

    @GetMapping("/edit-examination/{id}")
    public String showEditExaminationForm(@PathVariable Long id, Model model) {
        Examination examination = examinationService.getExaminationById(id);
        ExaminationViewModel viewModel = convertToViewModel(examination);
        model.addAttribute("examination", viewModel);
        populateDropdowns(model);
        return "examinations/edit-examination";
    }

    @PostMapping("/update/{id}")
    public String updateExamination(@PathVariable Long id,
                                    @Valid @ModelAttribute("examination") ExaminationViewModel examinationViewModel,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateDropdowns(model);
            return "examinations/edit-examination";
        }

        Examination examination = mapperUtil.getModelMapper().map(examinationViewModel, Examination.class);
        examination.setPatient(patientService.getPatientById(examinationViewModel.getPatientId()));
        examination.setDoctor(doctorService.getDoctorById(examinationViewModel.getDoctorId()));
        examination.setDiagnosis(diagnosisService.getDiagnosisById(examinationViewModel.getDiagnosisId()));

        examinationService.updateExamination(id, examination);
        return "redirect:/examinations";
    }

    @GetMapping("/delete/{id}")
    public String deleteExamination(@PathVariable Long id) {
        examinationService.deleteExamination(id);
        return "redirect:/examinations";
    }

    private ExaminationViewModel convertToViewModel(Examination examination) {
        ExaminationViewModel viewModel = new ExaminationViewModel();

        // Basic fields
        viewModel.setId(examination.getId());
        viewModel.setExaminationDate(examination.getExaminationDate());
        viewModel.setNotes(examination.getNotes());

        // Patient mapping
        if (examination.getPatient() != null) {
            viewModel.setPatientId(examination.getPatient().getId());
            viewModel.setPatientName(
                    examination.getPatient().getFirstName() + " " +
                            examination.getPatient().getLastName()
            );
        }

        // Doctor mapping
        if (examination.getDoctor() != null) {
            viewModel.setDoctorId(examination.getDoctor().getId());
            viewModel.setDoctorName(
                    examination.getDoctor().getFirstName() + " " +
                            examination.getDoctor().getLastName()
            );
        }

        // Diagnosis mapping
        if (examination.getDiagnosis() != null) {
            viewModel.setDiagnosisId(examination.getDiagnosis().getId());
            viewModel.setDiagnosisName(examination.getDiagnosis().getName());
        }

        return viewModel;
    }


    private void populateDropdowns(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("diagnoses", diagnosisService.getAllDiagnosis());
    }
}