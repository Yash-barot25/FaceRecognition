package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
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
    public String getDepartments(Model model, @RequestParam(value = "value", required = false, defaultValue = "") String val) {

        if (val != null && !val.trim().isEmpty()) {
            List<Department> student = departmentSDJpaService.searchDepartment(val);
            model.addAttribute("departments", departmentSDJpaService.searchDepartment(val));
        } else {
            model.addAttribute("departments", departmentSDJpaService.findAll());
        }

        model.addAttribute("institutes", instituteSDJpaService.findAll());
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

    @GetMapping("/delete/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId){

        departmentSDJpaService.deleteById(departmentId);

        return "redirect:/departments";
    }

}
