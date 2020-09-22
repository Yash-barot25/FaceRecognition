package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Experty;
import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ExpertiseSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProfessorSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorSDJpaService professorService;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ExpertiseSDJpaService expertiseSDJpaService;

    public ProfessorController(ProfessorSDJpaService professorService, DepartmentSDJpaService departmentSDJpaService, ExpertiseSDJpaService expertiseSDJpaService) {
        this.professorService = professorService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.expertiseSDJpaService = expertiseSDJpaService;
    }

    //shows all the professors
    @GetMapping({"", "/"})
    public String getProfessors(Model model) {
        model.addAttribute("professors", professorService.findAll());

        return "professor/professors";
    }

    //shows selected professor
    @GetMapping("/get/{professorId}")
    public String showStudentInfo(@PathVariable Long professorId, Model model) {

        model.addAttribute("professor", professorService.findById(professorId));
        return "professor/professor-info";
    }


    @GetMapping({"/update/{professorId}", "/create"})
    public String initUpdateStudentForm(@PathVariable(required = false) Long professorId, Model model) {
        if (professorId != null) {
            model.addAttribute("professor", professorService.findById(professorId));
        } else {
            Professor professor = new Professor();
            model.addAttribute("professor", professor);
        }

        model.addAttribute("departments", departmentSDJpaService.findAll());
        return "professor/createOrUpdateProfessor";
    }

    @PostMapping
    public String processUpdateProfessorForm(@ModelAttribute Professor professor) {

        Professor professor1 = professorService.save(professor);

        return "redirect:/professors/get/" + professor1.getId();
    }

    @GetMapping("/delete/{professorId}")
    public String deleteStudent(@PathVariable Long professorId) {

        professorService.deleteById(professorId);

        return "redirect:/professors";
    }

    //Expertise controllers

    @GetMapping("/{professorId}/expertise/add")
    public String showExpertise(@PathVariable Long professorId, Model model) {

        model.addAttribute("professor", professorService.findById(professorId));

        return "professor/expertise/expertise";

    }

    @GetMapping("/expertise/{professorId}/new")
    public String createOrUpdateExpertise(@PathVariable(required = false) Long professorId, Model model) {


        model.addAttribute("expertise", new Experty());

        return "professor/expertise/createOrUpdateExpertise";

    }

    @PostMapping("/expertise/save")
    public String saveExpertise(@ModelAttribute Experty experty, @PathVariable(required = false) Long professorId) {

        Experty experty1 = expertiseSDJpaService.save(experty);

        return "redirect:/" + experty1.getProfessor().getId() + "/expertise/add";

    }


}
