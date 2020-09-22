package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;

    public StudentController(StudentSDJpaService studentService, ProgramSDJpaService programService) {
        this.studentService = studentService;
        this.programService = programService;
    }

    //shows all the students
    @GetMapping({"", "/"})
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.findAll());

        return "student/students";
    }

    //shows selected student
    @GetMapping("/get/{studentId}")
    public String showStudentInfo(@PathVariable Long studentId, Model model) {

        model.addAttribute("student", studentService.findById(studentId));
        return "student/student-info";
    }


    @GetMapping({"/update/{studentId}", "/create"})
    public String initUpdateStudentForm(@PathVariable Optional<Long> studentId, Model model) {
        if (studentId.isPresent()){
            model.addAttribute("student",studentService.findById(studentId.get()));
        }else{
            Student student = new Student();
            model.addAttribute("student", student);
        }

        model.addAttribute("programs",programService.findAll());
        return "student/createOrUpdateStudent";
    }

    @PostMapping
    public String processUpdateStudentForm(@ModelAttribute Student student) {

       Student student1 = studentService.save(student);

       return "redirect:/students/get/" + student1.getId();
    }

    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId){

        studentService.deleteById(studentId);

        return "redirect:/students";
    }

}
