package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AWSClient;
import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.model.LogUsers;
import com.stealth.yash.FaceRecognition.model.Student;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class StudentLogController {
    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;
    private final AccessRepository accessRepository;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final ProgramSDJpaService programSDJpaService;
    private final AWSClient amclient;
    private final AccessSDJpaService accessSDJpaService;
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
    public String getLogs(Model model) {
        List<LocalDate> dateList = logUsersRepository.getValuesOfDistinctDates();

//        List<Map<String,  List<String[]>>> logs = new ArrayList<>();
        Map<String, List<String[]>> val1 = new HashMap<>();
        for (LocalDate localDate : dateList) {
            List<String[]> val2 = new ArrayList<>();
            List<LogUsers> logUsers = logUsersRepository.findAllByAccessDate(localDate);
            for (LogUsers logUser : logUsers) {

                AccessKey accessKey = accessRepository.findByAccessfobid(logUser.getUserFobId());

                Student student = studentRepository.findByAccessKey(accessKey);
                if(student!= null){
                    String v1 = student.getId().toString();
                    String v2 = student.getFirstName();
                    String v3 = logUser.getAccessTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String v4 = logUser.getUserFobId();

                    val2.add(new String[] {v1, v2, v3, v4});
                }

            }

            val1.put(localDate.toString(), val2);
        }

        model.addAttribute("logs", val1);


        return "studentlog/studentlog";


    }
}
