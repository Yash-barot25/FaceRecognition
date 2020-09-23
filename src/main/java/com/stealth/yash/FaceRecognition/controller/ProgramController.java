package com.stealth.yash.FaceRecognition.controller;


import com.stealth.yash.FaceRecognition.model.Program;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProfessorSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramSDJpaService programSDJpaService;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProfessorSDJpaService professorSDJpaService;

    public ProgramController(ProgramSDJpaService programSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProfessorSDJpaService professorSDJpaService) {
        this.programSDJpaService = programSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
    }

    @GetMapping({"", "/"})
    public String getPrograms(Model model) {
        model.addAttribute("programs", programSDJpaService.findAll());

        return "program/programs";
    }

    @GetMapping("/get/{programId}")
    public String showProgramInfo(@PathVariable Long programId, Model model) {

        model.addAttribute("program", programSDJpaService.findById(programId));
        return "program/program-info";
    }

    @GetMapping({"/update/{programId}", "/create"})
    public String createOrUpdateProgram(@PathVariable(required = false) Long programId, Model model) {
        if (programId != null) {
            model.addAttribute("program", programSDJpaService.findById(programId));
        } else {
            Program program = new Program();
            model.addAttribute("program", program);
        }

        model.addAttribute("departments", departmentSDJpaService.findAll());
        model.addAttribute("coordinators", professorSDJpaService.findAll());
        return "program/createOrUpdateProgram";
    }

    @PostMapping("")
    public String processUpdateProgramForm(@ModelAttribute Program program) {

        Program program1 = programSDJpaService.save(program);

        return "redirect:/programs/get/" + program1.getId();
    }

    @GetMapping("/delete/{programId}")
    public String deleteProgram(@PathVariable Long programId) {

        programSDJpaService.deleteById(programId);

        return "redirect:/programs";
    }

    @GetMapping("/by-departmentId")
    @ResponseBody
    public Set<Program> getProgramsByDepartmentId(@RequestParam Long departmentId) {
        return programSDJpaService.findProgramByDepartmentId(departmentId);
    }

}
