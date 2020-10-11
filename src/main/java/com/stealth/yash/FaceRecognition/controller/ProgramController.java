/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - ProgramController
 * This Controller is responsible for handling any request that is related to Programs.
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.model.Professor;
import com.stealth.yash.FaceRecognition.model.Program;
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
// Will create the base URI /programs for which the controller will be used
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramSDJpaService programSDJpaService;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProfessorSDJpaService professorSDJpaService;
    private final CourseSDJpaService courseSDJpaService;

    /**
     * This is a Program constructor
     * @param programSDJpaService this is an object of type ProgramSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param professorSDJpaService- an object of type ProfessorSDJpaService service
     * @param courseSDJpaService - an object of type CourseSDJpaService service
     */

    public ProgramController(ProgramSDJpaService programSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService) {
        this.programSDJpaService = programSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
    }

    /**
     * This is methods that shows all programs
     * @param model this is an object of type Model
     * @return program web page
     */
    @GetMapping({"", "/"})
    public String getPrograms(Model model) {
        model.addAttribute("programs", programSDJpaService.findAll());

        return "program/programs";
    }

    /**
     * This is a method that selected program info
     * @param programId this is an object of type Long
     * @param model - an object of Model
     * @return program info web page
     */
    @GetMapping("/get/{programId}")
    public String showProgramInfo(@PathVariable Long programId, Model model) {

        model.addAttribute("program", programSDJpaService.findById(programId));
        return "program/program-info";
    }

    /**
     * This is methat that creates or updates Programs
     * @param programId this is an object of type Long
     * @param model - an object of Model type
     * @return CreateOrupdateProgram web page
     */
    @GetMapping({"/update/{programId}", "/create"})
    public String createOrUpdateProgram(@PathVariable(required = false) Long programId, Model model) {
        if (programId != null) {
            model.addAttribute("program", programSDJpaService.findById(programId));
        } else {
            Program program = new Program();
            model.addAttribute("program", program);
        }

        model.addAttribute("departments", departmentSDJpaService.findAll());

        return "program/createOrUpdateProgram";
    }

    /**
     * This is a method that processes the data that ida updated in UpdateProgramForm
     * @param program this is an object of Program model type
     * @param bindingResult - an object of type BindingResult
     * @param model- an object of Model type
     * @return updated program info
     */
    @PostMapping("")
    public String processUpdateProgramForm(@Valid @ModelAttribute("program") Program program, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("departments", departmentSDJpaService.findAll());
            return "professor/createOrUpdateProfessor";
        }


        Program program1 = programSDJpaService.save(program);

        return "redirect:/programs/get/" + program1.getId();
    }

    /**
     * This mehtod deletes programs
     * @param programId this is an object of type Long
     * @return Programs web page
     */
    @GetMapping("/delete/{programId}")
    public String deleteProgram(@PathVariable Long programId) {

        Set<Professor> professors = professorSDJpaService.findProfessorsByProgramId(programId);
        for (Professor professor : professors){
            professor.setProgram(null);
        }
        Set<Course> courses = courseSDJpaService.findCoursesByProgramId(programId);
        for (Course course : courses){
            course.setProgram(null);
        }

        programSDJpaService.deleteById(programId);

        return "redirect:/programs";
    }

    /**
     *
     * @param departmentId this is an object of type Long
    **/
    @GetMapping("/by-departmentId")
    @ResponseBody
    public Set<Program> getProgramsByDepartmentId(@RequestParam Long departmentId) {
        return programSDJpaService.findProgramByDepartmentId(departmentId);
    }

}
