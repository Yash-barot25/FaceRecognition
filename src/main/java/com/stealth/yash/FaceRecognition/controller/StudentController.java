/**
 * ************************** FACIAL RECOGNITION - CAPSTONE************************
 * Controller - StudentController
 * This Controller is responsible for handling any request that is related to Students.
 *
 * @author STEALTH
 */

package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.enums.Roles;
import com.stealth.yash.FaceRecognition.model.*;
import com.stealth.yash.FaceRecognition.repository.RoleRepository;
import com.stealth.yash.FaceRecognition.repository.TokenRepository;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.ProgramSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import javax.transaction.Transactional;
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
    private final AccessSDJpaService accessSDJpaService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private static final Role studentRole = new Role();


    /**
     * This is a student constructor
     *
     * @param studentService         - an object of type StudentSDJpaService service
     * @param programService         - an object of type ProgramSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param programSDJpaService    - an object of type ProgramSDJpaService service
     * @param amclient               this is an object of type AWSClient model
     * @param accessSDJpaService
     * @param passwordEncoder
     */
    public StudentController(StudentSDJpaService studentService, TokenRepository tokenRepository, ProgramSDJpaService programService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, AWSClient amclient, AccessSDJpaService accessSDJpaService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.programService = programService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.amclient = amclient;
        this.accessSDJpaService = accessSDJpaService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    /**
     * This method shows all the students
     *
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
     *
     * @param studentId an object for studentID of type Long
     * @param model     an object of Model type
     * @return info of a particular student
     */
    @GetMapping("/get/{studentId}")
    public String showStudentInfo(@PathVariable Long studentId, Model model) throws UnsupportedEncodingException {
        Student student = new Student();
        String image = studentService.findById(studentId).getImage();
        model.addAttribute("userImage", image);
        model.addAttribute("student", studentService.findById(studentId));
        return "student/student-info";
    }

    /**
     * This method creates or updates student
     *
     * @param studentId an object for studentID of type Long
     * @param model     an object of Model type
     * @return creteOrUpdateStudent web page
     */
    @GetMapping({"/update/{studentId}", "/create"})
    public String createOrUpdateStudent(@PathVariable Optional<Long> studentId, Model model) {
        if (studentId.isPresent()) {
            model.addAttribute("student", studentService.findById(studentId.get()));
            List<AccessKey> accessKeys = accessSDJpaService.findAccessFobs();
            accessKeys.add(accessSDJpaService.findById(studentService.findById(studentId.get()).getAccessKey().getId()));

            model.addAttribute("accessKeys", accessKeys);
        } else {
            Student student = new Student();
            model.addAttribute("student", student);
            model.addAttribute("accessKeys", accessSDJpaService.findAccessFobs());
        }
        model.addAttribute("programs", programService.findAll());
        model.addAttribute("departments", departmentSDJpaService.findAll());

        return "student/createOrUpdateStudent";
    }

    /**
     * This method processes the data in Student Update form
     *
     * @param student       an object of Student model
     * @param bindingResult object of interface BindingResult
     * @param file          object of a Multipart file
     * @return updated student info
     */
    @PostMapping(consumes = "multipart/form-data")
    public String processUpdateStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @RequestPart(value = "file") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("programs", programService.findAll());
            model.addAttribute("departments", departmentSDJpaService.findAll());
            if (student.getId() != null) {

                List<AccessKey> accessKeys = accessSDJpaService.findAccessFobs();
                accessKeys.add(accessSDJpaService.findById(studentService.findById(student.getId()).getAccessKey().getId()));

                model.addAttribute("accessKeys", accessKeys);
            } else {

                model.addAttribute("accessKeys", accessSDJpaService.findAccessFobs());
            }
            return "student/createOrUpdateStudent";

        }

        if (!Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("image/png")) {
            model.addAttribute("programs", programService.findAll());
            model.addAttribute("departments", departmentSDJpaService.findAll());
            if (student.getId() != null) {

                List<AccessKey> accessKeys = accessSDJpaService.findAccessFobs();
                accessKeys.add(accessSDJpaService.findById(studentService.findById(student.getId()).getAccessKey().getId()));

                model.addAttribute("accessKeys", accessKeys);
            } else {

                model.addAttribute("accessKeys", accessSDJpaService.findAccessFobs());
            }
            bindingResult.rejectValue("image", "error.student", "You must upload a PNG image");
            return "student/createOrUpdateStudent";
        }

        if (student.getId() == null){
            if (studentService.findByEmail(student.getEmail()) != null) {
                System.out.println("Email Exists!!");
                model.addAttribute("programs", programService.findAll());
                model.addAttribute("departments", departmentSDJpaService.findAll());
                if (student.getId() != null) {

                    List<AccessKey> accessKeys = accessSDJpaService.findAccessFobs();
                    accessKeys.add(accessSDJpaService.findById(studentService.findById(student.getId()).getAccessKey().getId()));

                    model.addAttribute("accessKeys", accessKeys);
                } else {

                    model.addAttribute("accessKeys", accessSDJpaService.findAccessFobs());
                }
                bindingResult.rejectValue("email", "error.student", "An email already exist in STEALTH system.");


                return "student/createOrUpdateStudent";
            }
        }

        Token token = new Token();
        String fob = student.getAccessKey().getAccessfobid();
        student.setImage(amclient.uploadFile(file, fob));
        student.setStuPasswordEmail(generatePassword());
        String imagetoindex = student.getImage();
        String indexingimage = imagetoindex.substring(imagetoindex.lastIndexOf("/") + 1);
        String faceid = amclient.addfacetoawscollection(indexingimage);
        student.setFaceIdAWS(faceid);
        Student savedStudent = studentService.save(student);
        if (savedStudent == null) {
            log.error("NULL STUDENT PASSED");
        }
        if (student.getId() == null && savedStudent != null)  {
            User studentUser = new User();

            studentRole.setName(Roles.STUDENT);
            studentUser.setUseremail(savedStudent.getEmail());
            studentUser.setEnabled(false);
            studentUser.setRole(studentRole);
            studentUser.setPassword(passwordEncoder.encode(student.getStuPasswordEmail()));
            userRepository.save(studentUser);
            String confToken = UUID.randomUUID().toString();
            token.setConfirmationToken(confToken);
            token.setStudent(student);
            tokenRepository.save(token);
            emailTokenToUser(student.getEmail(), confToken);
        }


        assert savedStudent != null;
        return "redirect:/students/get/" + savedStudent.getId();
    }


    /**
     * This method generated password
     *
     * @return the generated password
     */
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


    /**
     * This method emails passwords to users
     *
     * @param to        an object of type String
     * @param confToken an object of type String
     * @return user's password
     */


    public String emailTokenToUser(String to, String confToken) {

        String from = "stealtht90@gmail.com";
        String pass = "Sheridan123";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject("Complete Registration - Stealth Admin");
            message.setText("To confirm your account, please click here : http://stealthsecurity.ca-central-1.elasticbeanstalk.com/confirm-account?token=" + confToken);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return confToken;
    }

    /**
     * This method deletes a student
     *
     * @param studentId an object of type Long
     * @return Students web page
     */


    @Transactional
    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId, AccessKey accessKey) {
        Student student = new Student();

        this.amclient.removeFile(studentService.findById(studentId).getImage());
        this.amclient.deletefacefromawscollection(studentService.findById(studentId).getFaceIdAWS());
        String fobid = studentService.findById(studentId).getAccessKey().getAccessfobid();
        String userEmail = studentService.findById(studentId).getEmail();
        userRepository.deleteUserByUseremail(userEmail);
        tokenRepository.deleteById(studentId);
        studentService.deleteById(studentId);
        accessKey.setAccessfobid(fobid);
        accessSDJpaService.save(accessKey);
        return "redirect:/students";
    }

}
