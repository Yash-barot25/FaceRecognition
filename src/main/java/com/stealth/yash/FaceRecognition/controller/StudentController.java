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
import java.util.*;

// Causes lombok to generate a logger field
@Slf4j
// Indicates that this class serves the role of a controller
@Controller
// Will create the base URI /students for which the controller will be used
@RequestMapping("/students")


public class StudentController {

    private final StudentSDJpaService studentService;
    private final ProgramSDJpaService programService;
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
    public StudentController(AWSClient amclient, StudentSDJpaService studentService, ProgramSDJpaService programService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, StudentSDJpaService studentSDJpaService, AccessSDJpaService accessSDJpaService) {
        this.studentService = studentService;
        this.programService = programService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.amclient = amclient;
        this.programSDJpaService = programSDJpaService;
        this.accessSDJpaService = accessSDJpaService;
    }

    /**
     * This method shows all the students
     * @param model - an object of type Model
     * @return student view
     */
    @GetMapping({"", "/"})
    public String getStudents(Model model, @RequestParam(value = "value", required = false, defaultValue = "") String val) {

        if (val != null && !val.trim().isEmpty()) {
            List<Student> student = studentService.searchStudent(val);
            model.addAttribute("students", studentService.searchStudent(val));
        } else {
            model.addAttribute("students", studentService.findAll());
        }
        model.addAttribute("departments", departmentSDJpaService.findAll());
        model.addAttribute("programs", programSDJpaService.findAll());

        return "student/students";
    }


    /**
     * This method shows selected student
     * @param studentId an object for studentID of type Long
     * @param model an object of Model type
     * @return info of a particular student
     */
    @GetMapping("/get/{studentId}")
    public String showStudentInfo(@PathVariable Long studentId, Model model) throws UnsupportedEncodingException {
        Student student = new Student();
        String image = studentService.findById(studentId).getImage();
        model.addAttribute("userImage",image);
        model.addAttribute("student", studentService.findById(studentId));
        return "student/student-info";
    }

    /**
     * This method creates or updates student
     * @param studentId an object for studentID of type Long
     * @param model an object of Model type
     * @return creteOrUpdateStudent web page
     */
    @GetMapping({"/update/{studentId}", "/create"})
    public String createOrUpdateStudent(@PathVariable Optional<Long> studentId, Model model) {
        if (studentId.isPresent()){
            model.addAttribute("student",studentService.findById(studentId.get()));
        }else{
            Student student = new Student();
            model.addAttribute("student", student);
        }
        model.addAttribute("programs",programService.findAll());
        model.addAttribute("departments",departmentSDJpaService.findAll());
        Set<AccessKey> accessKeys = accessSDJpaService.findAll();
        accessKeys.removeIf(accessKey1 -> studentService.findAccessFobIds().contains(accessKey1.getAccessfobid()));
        model.addAttribute("accessKeys", accessKeys);
        return "student/createOrUpdateStudent";
    }

    /**
     * This method processes the data in Student Update form
     * @param student an object of Student model
     * @param bindingResult object of interface BindingResult
     * @param file object of a Multipart file
     * @return updated student info
     */
    @PostMapping(consumes = "multipart/form-data")
    public String processUpdateStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @RequestPart(value = "file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("programs", programService.findAll());
            model.addAttribute("departments", departmentSDJpaService.findAll());
            return "student/createOrUpdateStudent";

        }
        Student savedStudent = null;
        if (!Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("image/png")) {
            System.out.println("Not a Proper Image type!!!");
        } else {

            String fob = student.getAccessKey().getAccessfobid();
            student.setImage(amclient.uploadFile(file,fob));
            student.setStuPasswordEmail(generatePassword());
            String imagetoindex = student.getImage();
            String indexingimage = imagetoindex.substring(imagetoindex.lastIndexOf("/") + 1);
            String faceid = amclient.addfacetoawscollection(indexingimage);
            student.setFaceIdAWS(faceid);
            savedStudent = studentService.save(student);

            // emailPasswordToUser(student1.getEmail(),student1.getStuPasswordEmail());
        }
        assert savedStudent != null;
        return "redirect:/students/get/" + savedStudent.getId();
    }

        /**
         * This method generated password
         * @return the generated password
         *
         */
    public String generatePassword(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = "";
        int maxlength = 8;
        for (int i=0; i<maxlength; i++){
            Random rand = new Random();
            int index = rand.nexnt(str.length());
            password += str.charAt(index);
        }
        return password;
    }

    /**
     * This method emails passwords to users
     * @param to an object of type String
     * @param password an object of type String
     * @return user's password
     */
    public String emailPasswordToUser (String to, String password){

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
            message.setRecipients(Message.RecipientType.TO,to);
            message.setSubject("Login Password - Stealth Admin");
            message.setText("Your password to access Stealth Admin Portal : " +password + "\n\n\nKind Regards,\n Team Stealth");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        return password;
    }

    /**
     * This method deletes a student
     * @param studentId an object of type Long
     * @return Students web page
     */
    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId){
        Student student = new Student();
        this.amclient.removeFile(studentService.findById(studentId).getImage());
        this.amclient.deletefacefromawscollection(studentService.findById(studentId).getFaceIdAWS());
        studentService.deleteById(studentId);
        return "redirect:/students";
    }

}
