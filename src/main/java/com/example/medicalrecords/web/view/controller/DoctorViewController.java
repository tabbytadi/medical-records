package com.example.medicalrecords.web.view.controller;

import com.example.medicalrecords.data.entity.Doctor;
import com.example.medicalrecords.dto.DoctorDTO;
import com.example.medicalrecords.service.DoctorService;
import com.example.medicalrecords.util.MapperUtil;
import com.example.medicalrecords.web.view.controller.model.DoctorViewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorViewController {
    private final DoctorService doctorService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String getAllDoctors(Model model) {
        List<DoctorViewModel> doctors = doctorService.getAllDoctors().stream()
                .map(doctor -> mapperUtil.getModelMapper().map(doctor, DoctorViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";
    }

    @GetMapping("/create-doctor")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorViewModel());
        return "/doctors/create-doctor";
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") DoctorViewModel doctorViewModel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/doctors/create-doctor";
        }
        doctorService.createDoctor(mapperUtil.getModelMapper().map(doctorViewModel, Doctor.class));
        return "redirect:/doctors";
    }

    @GetMapping("/edit-doctor/{id}")
    public String showEditDoctorForm(Model model, @PathVariable Long id) {
        DoctorDTO doctorDTO = mapperUtil.getModelMapper().map(doctorService.getDoctorById(id), DoctorDTO.class);
        model.addAttribute("doctor", mapperUtil.getModelMapper().map(doctorDTO, DoctorViewModel.class));
        return "/doctors/edit-doctor";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id,
                               @ModelAttribute DoctorViewModel doctorViewModel) {
        Doctor doctor = mapperUtil.getModelMapper().map(doctorViewModel, Doctor.class);
        doctorService.updateDoctor(id, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}