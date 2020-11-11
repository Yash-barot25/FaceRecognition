/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * This Controller is responsible for handling index page requests
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.repository.LogUsersRepository;
import com.stealth.yash.FaceRecognition.repository.StudentRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.LocalDate;
import java.util.*;

@Controller
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class IndexController {

   private final InstituteSDJpaService instituteSDJpaService;
   private final DepartmentSDJpaService departmentSDJpaService;
   private final ProgramSDJpaService programSDJpaService;
   private final ProfessorSDJpaService professorSDJpaService;
   private final CourseSDJpaService courseSDJpaService;
   private final StudentSDJpaService studentSDJpaService;
   private final LogUsersRepository logUsersRepository;
   private final StudentRepository  studentRepository;


    /**
     * This is a student constructor
     * @param instituteSDJpaService this is an object of type InstituteSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param programSDJpaService - an object of type ProgramSDJpaService service
     * @param studentSDJpaService - an object of type StudentSDJpaService service
     * @param logUsersRepository
     * @param studentRepository
     */

    public IndexController(InstituteSDJpaService instituteSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, StudentSDJpaService studentSDJpaService, LogUsersRepository logUsersRepository, StudentRepository studentRepository) {
        this.instituteSDJpaService = instituteSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.studentSDJpaService = studentSDJpaService;
        this.logUsersRepository = logUsersRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * This method shows main page
     * @return index page
     */
    @GetMapping({"/", "", "/index"})
    public String showMainPage(){
        return "index";

    }
    /**
     * This method displays contact information
     * @return contact web page
     */
    @GetMapping("/contact")
    public String contactUs(){
        return "contact";

    }
    /**
     * This method displays information about us
     * @return about web page
     */
    @GetMapping("/about")
    public String aboutUs(){
        return "about";

    }
    /**
     * This method directs to login page
     * @return login web page
     */
    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @GetMapping("/student")
    public String student(){
        return "student";

    }


    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Map<String, Long> studentAccessMap = new HashMap<>();
        Set<Student> students = studentSDJpaService.findAll();
        for(Student student :students){
            studentAccessMap.put(student.getFirstName(), logUsersRepository.countAllByUserFobId(student.getAccessKey().getAccessfobid()));
        }

        Map<LocalDate, Long> myMap = new TreeMap<>();
       List<LocalDate> dateList = logUsersRepository.getValuesOfDistinctDates();
        for (LocalDate localDate : dateList) {

            myMap.put(localDate, logUsersRepository.countAllByAccessDate(localDate));

        }


        model.addAttribute("institutes", instituteSDJpaService.findAll());
        model.addAttribute("departments", departmentSDJpaService.findAll());
        model.addAttribute("programs", programSDJpaService.findAll());
        model.addAttribute("professors", professorSDJpaService.findAll());
        model.addAttribute("courses", courseSDJpaService.findAll());
        model.addAttribute("students", students);

        model.addAttribute("studentAccessMap", studentAccessMap);
        model.addAttribute("timeFrame", "2020-2021");
        model.addAttribute("plotter", myMap);

        return "dashboard";
    }

    /**
     * This method displays coming soon section on Index page
     * @return index page
     */
    @GetMapping("/comingsoon")
    public String comingSoon(){
       return "comingsoon/index";
    }

    /**
     * This method manages pagination
     * @param pageNo an object of type int
     * @param model an object of type Model
     * @return students web page
     */
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