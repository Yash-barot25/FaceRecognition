package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.repository.StudentRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/fobmanager")
public class FobController {

    private final AccessSDJpaService accessSDJpaService;
    private final StudentSDJpaService studentSDJpaService;
    @Autowired
    StudentRepository studentRepository;

    public FobController(AccessSDJpaService accessSDJpaService, StudentSDJpaService studentSDJpaService) {
        this.accessSDJpaService = accessSDJpaService;

        this.studentSDJpaService = studentSDJpaService;
    }

    @GetMapping({"", "/"})
    public String accessPage(Model model) {
        model.addAttribute("students", studentSDJpaService.findAll());
        model.addAttribute("fobs", accessSDJpaService.findAccessFobs());
        model.addAttribute("check", accessSDJpaService.findAll());
        return "fobaccess/fobmanagement";
    }

    @GetMapping("/addaccesskey")
    public String addFob(Model model) {
        model.addAttribute("accesskey", new AccessKey());
        return "fobaccess/addfob";
    }

    @GetMapping("/deletefob/{id}")
    public String deleteFob(@PathVariable("id") Long fobId) {
        Student student = studentRepository.findStudentByAccessKey_Id(fobId);
        if (student != null) {
            student.setAccessKey(null);
        }
        accessSDJpaService.deleteById(fobId);
        return "redirect:/fobmanager";
    }


    @PostMapping("/savefob")
    public String addKey(@Valid @ModelAttribute("accesskey") AccessKey accesskey) {

        accessSDJpaService.save(accesskey);

        return "redirect:/fobmanager";


    }
}
