package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/showStudents")
    public String showStudents(Model model){

        model.addAttribute("students", studentService.findAll());

        return "students";

    }

}
