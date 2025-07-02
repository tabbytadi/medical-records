package com.example.medicalrecords.web.view.controller.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String getIndex(Model model) {
        final String welcomeMessage = "our digital medical records!";
        model.addAttribute("welcome", welcomeMessage);
        return "index";
    }

}
