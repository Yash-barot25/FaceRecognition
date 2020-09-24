package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.service.springdatajpa.CourseSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProfessorSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

   private final ProfessorSDJpaService professorSDJpaService;
   private final CourseSDJpaService courseSDJpaService;
   private final ProgramSDJpaService programSDJpaService;
   private final DepartmentSDJpaService departmentSDJpaService;


    public CourseController(ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, ProgramSDJpaService programSDJpaService, DepartmentSDJpaService departmentSDJpaService) {
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
    }

    //shows all the courses
    @GetMapping({"", "/"})
    public String getCourses(Model model) {
        model.addAttribute("courses", courseSDJpaService.findAll());
        return "course/courses";
    }

    //shows selected course
    @GetMapping("/get/{courseId}")
    public String showCourseInfo(@PathVariable Long courseId, Model model) {

        model.addAttribute("course", courseSDJpaService.findById(courseId));
        return "course/course-info";
    }


    @GetMapping({"/update/{courseId}", "/create"})
    public String initUpdateCourseForm(@PathVariable(required = false) Long courseId, Model model) {
        if (courseId != null){
            model.addAttribute("course",courseSDJpaService.findById(courseId));
        }else{
            Course course = new Course();
            model.addAttribute("course", course);
        }
        model.addAttribute("professors",professorSDJpaService.findAll());
        model.addAttribute("programs",programSDJpaService.findAll());
        model.addAttribute("departments",departmentSDJpaService.findAll());
        return "course/createOrUpdateCourse";
    }

    @PostMapping
    public String processUpdateCourseForm(@ModelAttribute Course course) {

        Course course1 = courseSDJpaService.save(course);

        return "redirect:/courses/get/" + course1.getId();
    }

    @GetMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable Long courseId){

        courseSDJpaService.deleteById(courseId);

        return "redirect:/courses";
    }


}
