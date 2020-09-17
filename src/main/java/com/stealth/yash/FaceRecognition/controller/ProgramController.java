package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.service.ProgramService;
import com.stealth.yash.FaceRecognition.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/program")
public class ProgramController {


    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/showPrograms")
    public String showStudents(Model model){

        model.addAttribute("programs", programService.findAll());

        return "programs";

    }

}
