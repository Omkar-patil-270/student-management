package com.example.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping({"/", "/students"})
    public String index(Model model) {
        model.addAttribute("appName", "Student Management System");
        model.addAttribute("version", "1.0.0");
        return "index";
    }
}
