package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String processUpdateProgramForm(@ModelAttribute Institute institute) {

        Institute institute1 = instituteSDJpaService.save(institute);

        return "redirect:/institutes/get/" + institute1.getId();
    }

    @GetMapping("/delete/{instituteId}")
    public String deleteInstitute(@PathVariable Long instituteId){

        instituteSDJpaService.deleteById(instituteId);

        return "redirect:/institutes";
    }
}
