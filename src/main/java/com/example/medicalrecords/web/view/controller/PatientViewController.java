package com.example.medicalrecords.web.view.controller;

import com.example.medicalrecords.data.entity.Patient;
import com.example.medicalrecords.dto.PatientDTO;
import com.example.medicalrecords.service.DoctorService;
import com.example.medicalrecords.service.PatientService;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.view.controller.model.PatientViewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientViewController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String getAllPatients(Model model) {
        List<PatientViewModel> patients = patientService.getAllPatients().stream()
                .map(patient -> mapperUtil.getModelMapper().map(patient, PatientViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);
        return "/patients/patients";
    }

    @GetMapping("/create-patient")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patient", new PatientViewModel());
        model.addAttribute("doctors", doctorService.getGpDoctors());
        return "/patients/create-patient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") PatientViewModel patientViewModel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/patients/create-patient";
        }
        patientService.createPatient(mapperUtil.getModelMapper().map(patientViewModel, Patient.class));
        return "redirect:/patients";
    }

    @GetMapping("/edit-patient/{id}")
    public String showEditPatientForm(Model model, @PathVariable Long id) {
        PatientDTO patientDTO = mapperUtil.getModelMapper().map(patientService.getPatientById(id), PatientDTO.class);
        model.addAttribute("patient", mapperUtil.getModelMapper().map(patientDTO, PatientViewModel.class));
        model.addAttribute("doctors", doctorService.getGpDoctors());
        return "/patients/edit-patient";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id,
                                @ModelAttribute PatientViewModel patientViewModel) {
        Patient patient = mapperUtil.getModelMapper().map(patientViewModel, Patient.class);
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}