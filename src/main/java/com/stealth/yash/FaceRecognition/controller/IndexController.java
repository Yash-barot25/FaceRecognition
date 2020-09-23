package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.CourseService;
import com.stealth.yash.FaceRecognition.service.ProfessorService;
import com.stealth.yash.FaceRecognition.service.ProgramService;
import com.stealth.yash.FaceRecognition.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class IndexController {

    private final StudentService studentService;
    private final ProgramService programService;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public IndexController(StudentService studentService, ProgramService programService, ProfessorService professorService, CourseService courseService) {
        this.studentService = studentService;
        this.programService = programService;
        this.professorService = professorService;
        this.courseService = courseService;
    }

    @GetMapping({"/"})
    public String mainPage(Model model) {

        model.addAttribute("programs", programService.findAll());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("professors", professorService.findAll());
        model.addAttribute("courses", courseService.findAll());

        return "index";
    }

   // @GetMapping("/getStudents/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Student> page = studentService.findPaginated(pageNo, pageSize);
        List<Student> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "students";
    }




}