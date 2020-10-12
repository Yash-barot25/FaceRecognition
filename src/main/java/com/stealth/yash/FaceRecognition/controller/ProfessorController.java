/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - ProfessorController
 * This Controller is responsible for handling any request that is related to Professor.
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.service.springdatajpa.CourseSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProfessorSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /professors for which the controller will be used
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorSDJpaService professorSDJpaService;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProgramSDJpaService programSDJpaService;
    private final CourseSDJpaService courseSDJpaService;
   // private final ExpertiseSDJpaService expertiseSDJpaService;

    /**
     * This is a Professor constructor
     * @param professorSDJpaService this is an object of type ProfessorSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param  programSDJpaService - an object of type ProgramSDJpaService service
     * @param courseSDJpaService - an object of type CourseSDJpaService service
     */
    public ProfessorController(ProfessorSDJpaService professorSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, CourseSDJpaService courseSDJpaService) {
        this.professorSDJpaService = professorSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
    }


    /**
     * This method shows all the professors
     * @param model - an object of type Model
     * @return Professors web page
     */
    @GetMapping({"", "/"})
    public String getProfessors(Model model) {
        model.addAttribute("professors", professorSDJpaService.findAll());

        return "professor/professors";
    }


    /**
     * This method shows selected professor
     * @param professorId an object of type Long
     * @param model - an object of type Model
     * @return info of a particular professor
     */
    @GetMapping("/get/{professorId}")
    public String showStudentInfo(@PathVariable Long professorId, Model model) {

        model.addAttribute("professor", professorSDJpaService.findById(professorId));
        return "professor/professor-info";
    }

    /**
     * This method creates or updates professors
     * @param professorId an object of type Long
     * @param model an object of Model type
     * @return creteOrUpdateProfessor web page
     */
    @GetMapping({"/update/{professorId}", "/create"})
    public String createOrUpdateProfessor(@PathVariable(required = false) Long professorId, Model model) {
        if (professorId != null) {
            model.addAttribute("professor", professorSDJpaService.findById(professorId));
        } else {
            Professor professor = new Professor();
            model.addAttribute("professor", professor);
        }

        model.addAttribute("departments", departmentSDJpaService.findAll());
        model.addAttribute("programs", programSDJpaService.findAll());
        return "professor/createOrUpdateProfessor";
    }

    /**
     * This method processes the updates data in UpdatePofessorForm
     * @param professor an object of Professor model
     * @param bindingResult object of interface BindingResult
     * @param model object of a type Model
     * @return updated professor info
     */
    @PostMapping("")
    public String processUpdateProfessorForm(@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("departments", departmentSDJpaService.findAll());
            model.addAttribute("programs", programSDJpaService.findAll());
            return "professor/createOrUpdateProfessor";
        }

        Professor professor1 = professorSDJpaService.save(professor);

        return "redirect:/professors/get/" + professor1.getId();
    }


    /**
     * This method deletes professor
     * @param professorId an object of type Long
     * @return professor web page
     */
    @GetMapping("/delete/{professorId}")
    public String deleteStudent(@PathVariable Long professorId) {

        Set<Course> courses = courseSDJpaService.findCoursesByProfessorId(professorId);
        for (Course course : courses){
            course.setProfessor(null);
        }

        professorSDJpaService.deleteById(professorId);

        return "redirect:/professors";
    }


    /**
     * @param departmentId this is an object of type Long
     **/
    @GetMapping("/by-departmentId")
    @ResponseBody
    public Set<Professor> getProfessorsByDepartmentId(@RequestParam Long departmentId) {
        Set<Professor> courses = professorSDJpaService.findProfessorsByDepartmentId(departmentId);
       return  professorSDJpaService.findProfessorsByDepartmentId(departmentId);
    }

    //Expertise controllers

//    @GetMapping("/{professorId}/expertise/add")
//    public String showExpertise(@PathVariable Long professorId, Model model) {
//
//        model.addAttribute("professor", professorService.findById(professorId));
//
//        return "professor/expertise/expertise";
//
//    }
//
//    @GetMapping("/expertise/{professorId}/new")
//    public String createOrUpdateExpertise(@PathVariable(required = false) Long professorId, Model model) {
//
//
//        model.addAttribute("expertise", new Experty());
//        model.addAttribute("professorId", professorId);
//
//        return "professor/expertise/createOrUpdateExpertise";
//
//    }
//
//    @PostMapping("/{professorId}/expertise/save")
//    @Transactional
//    public String saveExpertise(@ModelAttribute Experty experty, @PathVariable(required = false) Long professorId) {
//
//        Professor professor =  professorService.findById(professorId);
//
//        Experty experty1 = expertiseSDJpaService.save(experty);
//
//        experty1.setProfessor(professor);
//
//        professor.getExperties().add(experty1);
//
//        Professor professor1 =  professorService.save(professor);
//
//
//
//        return "redirect:/" +professor1.getId() + "/expertise/add";
//
//    }


}
