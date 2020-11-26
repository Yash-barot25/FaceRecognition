package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AWSClient;
import com.stealth.yash.FaceRecognition.repository.AccessRepository;
import com.stealth.yash.FaceRecognition.repository.LogUsersRepository;
import com.stealth.yash.FaceRecognition.repository.StudentRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
public class StudentLogController {
    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;
    private final AccessRepository accessRepository;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProgramSDJpaService programSDJpaService;
    private final AWSClient amclient;
    private  final AccessSDJpaService accessSDJpaService;
    private final LogUsersRepository logUsersRepository;
    private final StudentRepository studentRepository;

    public StudentLogController(AccessRepository accessRepository, AWSClient amclient, StudentSDJpaService studentService, ProgramSDJpaService programService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, StudentSDJpaService studentSDJpaService, AccessSDJpaService accessSDJpaService, LogUsersRepository logUsersRepository, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.programService = programService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.amclient = amclient;
        this.programSDJpaService = programSDJpaService;
        this.accessSDJpaService = accessSDJpaService;
        this.accessRepository = accessRepository;
        this.logUsersRepository = logUsersRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping("/studentlog")
    public String getLogs(Model model){
        List<LocalDate> dateList = logUsersRepository.getValuesOfDistinctDates();

        for (LocalDate localDate : dateList) {
            List<String> users = logUsersRepository.getStudents(localDate);
            model.addAttribute("users",users);
            model.addAttribute("students",studentRepository.findAllByAccessKey_Accessfobid("0003895811"));
        }

        model.addAttribute("allDates",dateList);


        return "studentlog/studentlog";
    }

}
