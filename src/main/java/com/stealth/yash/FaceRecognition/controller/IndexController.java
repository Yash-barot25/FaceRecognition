package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.springdatajpa.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

   private final InstituteSDJpaService instituteSDJpaService;
   private final DepartmentSDJpaService departmentSDJpaService;
   private final ProgramSDJpaService programSDJpaService;
   private final ProfessorSDJpaService professorSDJpaService;
   private final CourseSDJpaService courseSDJpaService;
   private final StudentSDJpaService studentSDJpaService;

    public IndexController(InstituteSDJpaService instituteSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, StudentSDJpaService studentSDJpaService) {
        this.instituteSDJpaService = instituteSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.studentSDJpaService = studentSDJpaService;
    }

    @GetMapping({"/", "", "/index"})
    public String showMainPage(){
        return "index";

    }

    @GetMapping("/contact")
    public String contactUs(){
        return "contact";

    }

    @GetMapping("/about")
    public String aboutUs(){
        return "about";

    }

    @GetMapping("/login")
    public String login(){
        return "login";

    }

//    @GetMapping("/dashboard")
//    public String showDashboard(Model model){
//
//        model.addAttribute("institutes", instituteSDJpaService.findAll());
//        model.addAttribute("departments", departmentSDJpaService.findAll());
//        model.addAttribute("programs", programSDJpaService.findAll());
//        model.addAttribute("professors", professorSDJpaService.findAll());
//        model.addAttribute("courses", courseSDJpaService.findAll());
//        model.addAttribute("students", studentSDJpaService.findAll());
//
//
//        return "dashboard";
//    }

    @GetMapping("/user")
    public String mainPage(Model model, Authentication authentication) {

        String name = authentication.getName();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga: authentication.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        model.addAttribute("name", name);
        model.addAttribute("roles", roles);
        model.addAttribute("programs", programSDJpaService.findAll());
        model.addAttribute("students", studentSDJpaService.findAll());
        model.addAttribute("professors", professorSDJpaService.findAll());
        model.addAttribute("courses", courseSDJpaService.findAll());
        model.addAttribute("institutes", instituteSDJpaService.findAll());
        model.addAttribute("departments", departmentSDJpaService.findAll());

        return "user/index";
    }

    @GetMapping("/comingsoon")
    public String comingSoon(){
       return "comingsoon/index";
    }

   // @GetMapping("/getStudents/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Student> page = studentSDJpaService.findPaginated(pageNo, pageSize);
        List<Student> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "students";
    }




}