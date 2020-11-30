/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * This Controller is responsible for handling index page requests
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;

import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.model.Token;
import com.stealth.yash.FaceRecognition.model.User;
import com.stealth.yash.FaceRecognition.repository.LogUsersRepository;
import com.stealth.yash.FaceRecognition.repository.StudentRepository;
import com.stealth.yash.FaceRecognition.repository.TokenRepository;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import com.stealth.yash.FaceRecognition.service.springdatajpa.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
   private final UserRepository userRepository;
   private final TokenRepository tokenRepository;


    /**
     * This is a student constructor
     * @param instituteSDJpaService this is an object of type InstituteSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param programSDJpaService - an object of type ProgramSDJpaService service
     * @param studentSDJpaService - an object of type StudentSDJpaService service
     * @param logUsersRepository
     * @param studentRepository
     * @param userRepository
     * @param tokenRepository
     */

    public IndexController(InstituteSDJpaService instituteSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, StudentSDJpaService studentSDJpaService, LogUsersRepository logUsersRepository, StudentRepository studentRepository, UserRepository userRepository, TokenRepository tokenRepository) {
        this.instituteSDJpaService = instituteSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.studentSDJpaService = studentSDJpaService;
        this.logUsersRepository = logUsersRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        Token token = tokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null)
        {
            User studentUser = userRepository.findUserByUseremail(token.getStudent().getEmail()).orElseThrow(() -> new UserNotFoundException("User with  Not found")) ;
            studentUser.setEnabled(true);
            userRepository.save(studentUser);
            emailPasswordToUser(token.getStudent().getEmail(),token.getStudent().getStuPasswordEmail());
            return "accountVerified";
        }
        else
        {
            return "error/access-denied";
        }

    }

    public String emailPasswordToUser(String to, String password) {
        String from = "stealtht90@gmail.com";
        String pass = "Sheridan123";

        String SmtpUsername = "AKIAZOEIQOANFQBBLMHA";
        String smtpPassword = "BKL6JgIvFG3QBCuXSWh+uuZTdL6ezkkcdGPAL9ceGfxF";
        Properties props = System.getProperties();
        String host = "email-smtp.ca-central-1.amazonaws.com";
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject("Login Password - Stealth Admin");
            message.setText("Your password to access Stealth Admin Portal : " + password +"\n\n\nKind Regards,\n Team Stealth");
            Transport transport = session.getTransport();
            transport.connect(host, SmtpUsername, smtpPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return password;
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