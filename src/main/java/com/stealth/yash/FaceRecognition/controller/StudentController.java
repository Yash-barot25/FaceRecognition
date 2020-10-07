package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AWSClient;
import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.repository.AccessRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;
    private final DepartmentSDJpaService departmentSDJpaService;
    private final AccessSDJpaService accessSDJpaService;
    private AccessRepository accessRepository;
    private final AWSClient amclient;
    String faceid = "";

    public StudentController(AWSClient amclient, StudentSDJpaService studentService, ProgramSDJpaService programService, DepartmentSDJpaService departmentSDJpaService, AccessSDJpaService accessSDJpaService) {
        this.studentService = studentService;
        this.programService = programService;
        this.departmentSDJpaService = departmentSDJpaService;

        this.amclient = amclient;
        this.accessSDJpaService = accessSDJpaService;
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public List<String> test(){
//
//        studentService.findAccessFobIds().forEach(error -> log.error(error.toString()));
//        List<String> keys =  studentService.findAccessFobIds();
//     return  studentService.findAccessFobIds();
//    }


    //shows all the students
    @GetMapping({"", "/"})
    public String getStudents(Model model) {
//        Student student = new Student();
//        String image = studentService.findById(student.getId()).getImage();
//        model.addAttribute("userImage",image);
        model.addAttribute("students", studentService.findAll());

        return "student/students";
    }

    //shows selected student
    @GetMapping("/get/{studentId}")
    public String showStudentInfo(@PathVariable("studentId") Long studentId, Model model) throws UnsupportedEncodingException {
        String image = studentService.findById(studentId).getImage();
        model.addAttribute("userImage", image);
        model.addAttribute("student", studentService.findById(studentId));
        return "student/student-info";
    }


    @GetMapping({"/update/{studentId}", "/create"})
    public String createOrUpdateStudent(@PathVariable Optional<Long> studentId, Model model) {
        Student student = new Student();
        if (studentId.isPresent()) {
            model.addAttribute("student", studentService.findById(studentId.get()));
        } else {

            model.addAttribute("student", student);
        }


        model.addAttribute("programs", programService.findAll());
        model.addAttribute("departments", departmentSDJpaService.findAll());
        Set<AccessKey> accessKeys = accessSDJpaService.findAll();
        accessKeys.removeIf(accessKey1 -> studentService.findAccessFobIds().contains(accessKey1.getAccessfobid()));


        model.addAttribute("accessKeys", accessKeys);
        return "student/createOrUpdateStudent";
    }

    @PostMapping(consumes = "multipart/form-data")
    public String processUpdateStudentForm(@Valid @ModelAttribute("student") Student student1, BindingResult bindingResult, @RequestPart(value = "file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            return "student/createOrUpdateStudent";

        }

        if (!file.getContentType().equalsIgnoreCase("image/png")) {
            System.out.println("Not a Proper Image type!!!");
        } else {

            String fob = student1.getAccessKey().getAccessfobid();
            student1.setImage(amclient.uploadFile(file, fob));
            student1.setStuPasswordEmail(generatePassword());

            student1 = studentService.save(student1);
            String imagetoindex = studentService.findById(student1.getId()).getImage();
            String indexingimage = imagetoindex.substring(imagetoindex.lastIndexOf("/") + 1);
            faceid = amclient.addfacetoawscollection(indexingimage);
            // emailPasswordToUser(student1.getEmail(),student1.getStuPasswordEmail());
        }

        return "redirect:/students/get/" + student1.getId();
    }


    public String generatePassword() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = "";
        int maxlength = 8;
        for (int i = 0; i < maxlength; i++) {
            Random rand = new Random();
            int index = rand.nextInt(str.length());
            password += str.charAt(index);
        }
        return password;
    }

    public String emailPasswordToUser(String to, String password) {

        String from = "stealtht90@gmail.com";
        String pass = "Sheridan123";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject("Login Password - Stealth Admin");
            message.setText("Your password to access Stealth Admin Portal : " + password + "\n\n\nKind Regards,\n Team Stealth");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return password;
    }

    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        Student student = new Student();
        this.amclient.removeFile(studentService.findById(studentId).getImage());
        this.amclient.deletefacefromawscollection(faceid);
        studentService.deleteById(studentId);
        return "redirect:/students";
    }

}
