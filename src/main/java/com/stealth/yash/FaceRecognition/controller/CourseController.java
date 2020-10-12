/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - CourseController
 * This Controller is responsible for handling any request that is related to Course.
 * @author  STEALTH
 *
 */

package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Course;
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

// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /students for which the controller will be used
@RequestMapping("/courses")

public class CourseController {

   private final ProfessorSDJpaService professorSDJpaService;
   private final CourseSDJpaService courseSDJpaService;
   private final ProgramSDJpaService programSDJpaService;
   private final DepartmentSDJpaService departmentSDJpaService;

    /**
     * This is a course constructor
     * @param professorSDJpaService this is an object of type ProgramSDJpaService service
     * @param courseSDJpaService - an object of type CourseSDJpaService service
     * @param programSDJpaService- an object of type ProgramSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     */
    public CourseController(ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, ProgramSDJpaService programSDJpaService, DepartmentSDJpaService departmentSDJpaService) {
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
    }

    /**
     * This method shows all courses
     * @param model an object of type model
     * @return courses web page
     */
    @GetMapping({"", "/"})
    public String getCourses(Model model) {
        model.addAttribute("courses", courseSDJpaService.findAll());
        return "course/courses";
    }

    /**
     * This method shows selected course
     * @param courseId  an object of type Long
     * @param model an object of type Model
     * @return selected course info
     */

    @GetMapping("/get/{courseId}")
    public String showCourseInfo(@PathVariable Long courseId, Model model) {

        model.addAttribute("course", courseSDJpaService.findById(courseId));
        return "course/course-info";
    }

    /**
     * This method creates or updates courses
     * @param courseId this is an object of type Long
     * @param model - an object of Model type
     * @return createOrUpdateCourse web page
     */
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

    /**
     * This method processes the data in Student Update form
     * @param course an object of Course model type
     * @param bindingResult object of interface BindingResult
     * @param model an object of Model type
     * @return updated course info
     */
    @PostMapping("")
    public String processUpdateCourseForm(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("professors",professorSDJpaService.findAll());
            model.addAttribute("programs",programSDJpaService.findAll());
            model.addAttribute("departments",departmentSDJpaService.findAll());
            return "course/createOrUpdateCourse";
        }

        Course course1 = courseSDJpaService.save(course);

        return "redirect:/courses/get/" + course1.getId();
    }


    /**
     * This method deletes courses
     * @param courseId an object of type Long
     * @return redirects back to courses web page
     */
    @GetMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable Long courseId){

        courseSDJpaService.deleteById(courseId);

        return "redirect:/courses/";
    }


}
