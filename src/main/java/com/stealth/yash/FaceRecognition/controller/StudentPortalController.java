/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - StudentController
 * This Controller is responsible for handling any request that is related to Students.
 * @author  STEALTH
 *
 */

package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AWSClient;
import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.repository.AccessRepository;
import com.stealth.yash.FaceRecognition.service.StudentService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /students for which the controller will be used
@RequestMapping("/student")


public class StudentPortalController {

    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;
    private final AccessRepository accessRepository;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProgramSDJpaService programSDJpaService;
    private final AWSClient amclient;
    private  final AccessSDJpaService accessSDJpaService;

    /**
     * This is a student constructor
     * @param amclient this is an object of type AWSClient model
     * @param studentService - an object of type StudentSDJpaService service
     * @param programService - an object of type ProgramSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param programSDJpaService - an object of type ProgramSDJpaService service
     * @param studentSDJpaService
     * @param accessSDJpaService
     */
    public StudentPortalController(AccessRepository accessRepository, AWSClient amclient, StudentSDJpaService studentService, ProgramSDJpaService programService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, StudentSDJpaService studentSDJpaService, AccessSDJpaService accessSDJpaService) {
        this.studentService = studentService;
        this.programService = programService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.amclient = amclient;
        this.programSDJpaService = programSDJpaService;
        this.accessSDJpaService = accessSDJpaService;
        this.accessRepository = accessRepository;
    }

    @GetMapping("/detail")
    public String getStudentDetail(Principal principal, Model model, Authentication auth) {
        String currentLoggedinUser  = principal.getName();
        Student student = studentService.findByEmail(currentLoggedinUser);
        model.addAttribute("userImage",student.getImage());
        model.addAttribute("student",student);
        return "student/studentDashboard";
    }
}
