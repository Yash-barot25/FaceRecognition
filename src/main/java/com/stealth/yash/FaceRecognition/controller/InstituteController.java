/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - InstituteController
 * This Controller is responsible for handling any request that is related to Institutes
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.AWSClient;
import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.StudentService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /institutes for which the controller will be used
@RequestMapping("/institutes")
public class InstituteController {

    private final InstituteSDJpaService instituteSDJpaService;
    private final StudentSDJpaService studentSDJpaService;
    private AWSClient amclient;

    /**
     * This is a student constructor
     * @param amclient this is an object of type AWSClient model
     * @param studentSDJpaService - an object of type StudentSDJpaService service
     * @param instituteSDJpaService - an object of type InstituteSDJpaService service
     */
    public InstituteController(AWSClient amclient, StudentSDJpaService studentSDJpaService,InstituteSDJpaService instituteSDJpaService) {
        this.instituteSDJpaService = instituteSDJpaService;
        this.amclient = amclient;
        this.studentSDJpaService = studentSDJpaService;
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



    /**
     * This method shows all the Institutes
     * @param model - an object of type Model
     * @return Institutes web page
     */
    @GetMapping({"", "/"})
    public String getInstitutes(Model model) {


        model.addAttribute("institutes", instituteSDJpaService.findAll());

        return "institute/institutes";
    }

    /**
     * This method shows selected Institutes
     * @param instituteId an object of type Long
     * @param model an object of Model type
     * @return info of a particular institute
     */
    @GetMapping("/get/{instituteId}")
    public String showProgramInfo(@PathVariable Long instituteId, Model model) {


        model.addAttribute("institute", instituteSDJpaService.findById(instituteId));
        return "institute/institute-info";
    }

    /**
     * This method creates or updates Institute
     * @param instituteId an object of type Long
     * @param model an object of Model type
     * @return creteOrUpdateInstitue web page
     */
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


    /**
     * This method processes the data in Insitute Update form
     * @param institute an object of Institute model type
     * @param bindingResult object of interface BindingResult
     * @return updated Institute info
     */
    @PostMapping("")
    public String processUpdateProgramForm(@Valid @ModelAttribute("institute") Institute institute, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            return "institute/createOrUpdateInstitute";

        }

        Institute institute1 = instituteSDJpaService.save(institute);

        return "redirect:/institutes/get/" + institute1.getId();
    }


    /**
     * This method deletes an Institute
     * @param instituteId an object of type Long
     * @return Institutes web page
     */
    @GetMapping("/delete/{instituteId}")
    public String deleteInstitute(@PathVariable Long instituteId){
        instituteSDJpaService.deleteById(instituteId);
        return "redirect:/institutes";
    }
}
