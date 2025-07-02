package com.example.medicalrecords.web.view.controller;

import com.example.medicalrecords.data.entity.SickLeave;
import com.example.medicalrecords.service.ExaminationService;
import com.example.medicalrecords.service.SickLeaveService;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.view.controller.model.SickLeaveViewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sick-leaves")
@RequiredArgsConstructor
public class SickLeaveViewController {
    private final SickLeaveService sickLeaveService;
    private final ExaminationService examinationService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String getAllSickLeaves(Model model) {
        List<SickLeaveViewModel> sickLeaves = sickLeaveService.getAllSickLeaves().stream()
                .map(this::convertToViewModel)
                .collect(Collectors.toList());
        model.addAttribute("sickLeaves", sickLeaves);
        return "sick-leaves/sick-leaves";
    }

    @GetMapping("/create-sick-leave")
    public String showCreateSickLeaveForm(Model model) {
        model.addAttribute("sickLeave", new SickLeaveViewModel());
        model.addAttribute("examinations", examinationService.getAllExaminations());
        return "sick-leaves/create-sick-leave";
    }

    @PostMapping("/create")
    public String createSickLeave(@Valid @ModelAttribute("sickLeave") SickLeaveViewModel sickLeaveViewModel,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("examinations", examinationService.getAllExaminations());
            return "sick-leaves/create-sick-leave";
        }

        SickLeave sickLeave = mapperUtil.getModelMapper().map(sickLeaveViewModel, SickLeave.class);
        sickLeave.setExamination(examinationService.getExaminationById(sickLeaveViewModel.getExaminationId()));
        sickLeaveService.createSickLeave(sickLeave);
        return "redirect:/sick-leaves";
    }

    @GetMapping("/edit-sick-leave/{id}")
    public String showEditSickLeaveForm(@PathVariable Long id, Model model) {
        SickLeave sickLeave = sickLeaveService.getSickLeaveById(id);
        SickLeaveViewModel viewModel = convertToViewModel(sickLeave);
        model.addAttribute("sickLeave", viewModel);
        model.addAttribute("examinations", examinationService.getAllExaminations());
        return "sick-leaves/edit-sick-leave";
    }

    @PostMapping("/update/{id}")
    public String updateSickLeave(@PathVariable Long id,
                                  @Valid @ModelAttribute("sickLeave") SickLeaveViewModel sickLeaveViewModel,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("examinations", examinationService.getAllExaminations());
            return "sick-leaves/edit-sick-leave";
        }

        SickLeave sickLeave = mapperUtil.getModelMapper().map(sickLeaveViewModel, SickLeave.class);
        sickLeave.setExamination(examinationService.getExaminationById(sickLeaveViewModel.getExaminationId()));
        sickLeaveService.updateSickLeave(id, sickLeave);
        return "redirect:/sick-leaves";
    }

    @GetMapping("/delete/{id}")
    public String deleteSickLeave(@PathVariable Long id) {
        sickLeaveService.deleteSickLeave(id);
        return "redirect:/sick-leaves";
    }

    private SickLeaveViewModel convertToViewModel(SickLeave sickLeave) {
        SickLeaveViewModel viewModel = mapperUtil.getModelMapper().map(sickLeave, SickLeaveViewModel.class);
        viewModel.setExaminationId(sickLeave.getExamination().getId());
        viewModel.setDurationDays(sickLeave.getDaysCount());
        return viewModel;
    }
}