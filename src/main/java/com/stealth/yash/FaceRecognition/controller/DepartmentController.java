/**
 ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - DepartmentController
 * This Controller is responsible for handling any request that is related to Departments.
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
/**
 *
 * This Controller is responsible for handling any request that is related to Students.
 * @author  STEALTH
 *
 */

import javax.validation.Valid;
import java.util.Set;


// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /departments for which the controller will be used
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentSDJpaService departmentSDJpaService;
    private final InstituteSDJpaService instituteSDJpaService;

    /**
     * This is a department constructor
     * @param departmentSDJpaService this is an object of type DepartmentSDJpaService service
     * @param instituteSDJpaService - an object of type InstituteSDJpaService service
     *
     */
    public DepartmentController(DepartmentSDJpaService departmentSDJpaService, InstituteSDJpaService instituteSDJpaService) {
        this.departmentSDJpaService = departmentSDJpaService;
        this.instituteSDJpaService = instituteSDJpaService;
    }


    /**
     * This method shows all the students
     * @param model - an object of type Model
     * @return departments web page
     */
    @GetMapping({"", "/"})
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentSDJpaService.findAll());

        return "department/departments";
    }

    /**
     * This method shows selected department
     * @param departmentId an object of type Long
     * @param model an object of Model type
     * @return info of a particular department
     */
    @GetMapping("/get/{departmentId}")
    public String showProgramInfo(@PathVariable Long departmentId, Model model) {

        model.addAttribute("department", departmentSDJpaService.findById(departmentId));
        return "department/department-info";
    }

    /**
     * This method updates Program info
     * @param departmentId an object of type Long
     * @param model an object of Model type
     * @return creteOrUpdateStudent web page
     */
    @GetMapping({"/update/{departmentId}", "/create"})
    public String initUpdateProgramForm(@PathVariable(required = false) Long departmentId, Model model) {
        if (departmentId != null){
            model.addAttribute("department",departmentSDJpaService.findById(departmentId));
        }else{

          model.addAttribute("department", new Department());
        }

        Set<Institute> instituteSet = instituteSDJpaService.findAll();
        model.addAttribute("institutes",instituteSDJpaService.findAll() );

        return "department/createOrUpdateDepartment";
    }

    /**
     * This method processes the data in Student Update form
     * @param department an object of type Department model
     * @param bindingResult object of interface BindingResult
     * @param model object of Model type
     * @return updated department info
     */
    @PostMapping("")
    public String processUpdateProgramForm(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult , Model model) {

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("institutes",instituteSDJpaService.findAll() );
            return "department/createOrUpdateDepartment";

        }

       Department department1 = departmentSDJpaService.save(department);

        return "redirect:/departments/get/" + department1.getId();
    }

    /**
     * This method deletes a department
     * @param departmentId an object of type Long
     * @return department Web page
     */
    @GetMapping("/delete/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId){

        departmentSDJpaService.deleteById(departmentId);

        return "redirect:/departments";
    }

}
