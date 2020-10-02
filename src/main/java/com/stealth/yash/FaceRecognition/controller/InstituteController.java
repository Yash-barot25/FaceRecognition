package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/institutes")
public class InstituteController {

    private final InstituteSDJpaService instituteSDJpaService;

    public InstituteController(InstituteSDJpaService instituteSDJpaService) {
        this.instituteSDJpaService = instituteSDJpaService;
    }

//    @GetMapping("/get")
//    public String getInstitute(Model model){
//
//        model.addAttribute("institute", new Institute());
//
//        return "createOrUpdateInstitute";
//
//    }
//
//
//    @PostMapping("")
//    public String saveInstitute(@ModelAttribute Institute institute){
//
//      Institute institute1 =   instituteSDJpaService.save(institute);
//
//      return "redirect:/";
//
//    }

    @GetMapping({"", "/"})
    public String getInstitutes(Model model) {


        model.addAttribute("institutes", instituteSDJpaService.findAll());

        return "institute/institutes";
    }

    @GetMapping("/get/{instituteId}")
    public String showProgramInfo(@PathVariable Long instituteId, Model model) {


        model.addAttribute("institute", instituteSDJpaService.findById(instituteId));
        return "institute/institute-info";
    }

    @GetMapping({"/update/{instituteId}", "/create"})
    public String initUpdateProgramForm(@PathVariable(required = false) Long instituteId, Model model) {
        if (instituteId != null){
            model.addAttribute("institute",instituteSDJpaService.findById(instituteId));
        }else{

            model.addAttribute("institute", new Institute());
        }

        ;
        return "institute/createOrUpdateInstitute";
    }

    @PostMapping("")
    public String processUpdateProgramForm(@Valid @ModelAttribute("institute") Institute institute, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            return "institute/createOrUpdateInstitute";

        }

        Institute institute1 = instituteSDJpaService.save(institute);

        return "redirect:/institutes/get/" + institute1.getId();
    }

    @GetMapping("/delete/{instituteId}")
    public String deleteInstitute(@PathVariable Long instituteId){

        instituteSDJpaService.deleteById(instituteId);

        return "redirect:/institutes";
    }
}
