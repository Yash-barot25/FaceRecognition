package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentSDJpaService departmentSDJpaService;
    private final InstituteSDJpaService instituteSDJpaService;


    public DepartmentController(DepartmentSDJpaService departmentSDJpaService, InstituteSDJpaService instituteSDJpaService) {
        this.departmentSDJpaService = departmentSDJpaService;
        this.instituteSDJpaService = instituteSDJpaService;
    }

    @GetMapping({"", "/"})
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentSDJpaService.findAll());

        return "department/departments";
    }

    @GetMapping("/get/{departmentId}")
    public String showProgramInfo(@PathVariable Long departmentId, Model model) {

        model.addAttribute("department", departmentSDJpaService.findById(departmentId));
        return "department/department-info";
    }

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

    @PostMapping
    public String processUpdateProgramForm(@ModelAttribute Department department) {

       Department department1 = departmentSDJpaService.save(department);

        return "redirect:/departments/get/" + department1.getId();
    }

    @GetMapping("/delete/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId){

        departmentSDJpaService.deleteById(departmentId);

        return "redirect:/departments";
    }

}
