package com.example.medicalrecords.web.view.controller;

import com.example.medicalrecords.data.entity.Diagnosis;
import com.example.medicalrecords.service.DiagnosisService;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.view.controller.model.DiagnosisViewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisViewController {
    private final DiagnosisService diagnosisService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String getAllDiagnosis(Model model) {
        List<DiagnosisViewModel> diagnosis = diagnosisService.getAllDiagnosis().stream()
                .map(diagnoses -> mapperUtil.getModelMapper().map(diagnoses, DiagnosisViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("diagnosis", diagnosis);
        return "diagnosis/diagnosis";
    }

    @GetMapping("/create-diagnosis")
    public String showCreateDiagnosisForm(Model model) {
        model.addAttribute("diagnosis", new DiagnosisViewModel());
        return "diagnosis/create-diagnosis";
    }

    @PostMapping("/create")
    public String createDiagnosis(@Valid @ModelAttribute("diagnosis") DiagnosisViewModel diagnosisViewModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "diagnosis/create-diagnosis";
        }
        diagnosisService.createDiagnosis(mapperUtil.getModelMapper().map(diagnosisViewModel, Diagnosis.class));
        return "redirect:/diagnosis";
    }

    @GetMapping("/edit-diagnosis/{id}")
    public String showEditDiagnosisForm(@PathVariable Long id, Model model) {
        DiagnosisViewModel viewModel = mapperUtil.getModelMapper().map(
                diagnosisService.getDiagnosisById(id),
                DiagnosisViewModel.class
        );
        model.addAttribute("diagnosis", viewModel);
        return "diagnosis/edit-diagnosis";
    }

    @PostMapping("/update/{id}")
    public String updateDiagnosis(@PathVariable Long id,
                                  @Valid @ModelAttribute("diagnosis") DiagnosisViewModel diagnosisViewModel,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "diagnosis/edit-diagnosis";
        }
        Diagnosis diagnosis = mapperUtil.getModelMapper().map(diagnosisViewModel, Diagnosis.class);
        diagnosisService.updateDiagnosis(id, diagnosis);
        return "redirect:/diagnosis";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return "redirect:/diagnosis";
    }
}