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
import com.stealth.yash.FaceRecognition.repository.TokenRepository;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import com.stealth.yash.FaceRecognition.service.StudentService;
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
import java.util.List;
import java.util.Properties;

@Controller
public class IndexController {

   private final InstituteSDJpaService instituteSDJpaService;
   private final DepartmentSDJpaService departmentSDJpaService;
   private final ProgramSDJpaService programSDJpaService;
   private final ProfessorSDJpaService professorSDJpaService;
   private final CourseSDJpaService courseSDJpaService;
   private final StudentSDJpaService studentSDJpaService;
   private final LogUsersRepository logUsersRepository;
   private final UserRepository userRepository;
   private final StudentService studentService;
   private final TokenRepository tokenRepository;

    /**
     * This is a student constructor
     * @param instituteSDJpaService this is an object of type InstituteSDJpaService service
     * @param departmentSDJpaService - an object of type DepartmentSDJpaService service
     * @param programSDJpaService - an object of type ProgramSDJpaService service
     * @param studentSDJpaService - an object of type StudentSDJpaService service
     * @param logUsersRepository
     * @param userRepository
     * @param studentService
     * @param tokenRepository
     */

    public IndexController(InstituteSDJpaService instituteSDJpaService, DepartmentSDJpaService departmentSDJpaService, ProgramSDJpaService programSDJpaService, ProfessorSDJpaService professorSDJpaService, CourseSDJpaService courseSDJpaService, StudentSDJpaService studentSDJpaService, LogUsersRepository logUsersRepository, UserRepository userRepository, StudentService studentService, TokenRepository tokenRepository) {
        this.instituteSDJpaService = instituteSDJpaService;
        this.departmentSDJpaService = departmentSDJpaService;
        this.programSDJpaService = programSDJpaService;
        this.professorSDJpaService = professorSDJpaService;
        this.courseSDJpaService = courseSDJpaService;
        this.studentSDJpaService = studentSDJpaService;
        this.logUsersRepository = logUsersRepository;
        this.userRepository = userRepository;
        this.studentService = studentService;
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
            message.setText("Your password to access Stealth Admin Portal : " + password +"\n\n\nKind Regards,\n Team Stealth");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return password;
    }


    @GetMapping("/dashboard")
    public String showDashboard(Model model){

        model.addAttribute("count",logUsersRepository.findCount().toString());

        model.addAttribute("institutes", instituteSDJpaService.findAll());
        model.addAttribute("departments", departmentSDJpaService.findAll());
        model.addAttribute("programs", programSDJpaService.findAll());
        model.addAttribute("professors", professorSDJpaService.findAll());
        model.addAttribute("courses", courseSDJpaService.findAll());
        model.addAttribute("students", studentSDJpaService.findAll());


        return "dashboard";
    }


//    /**
//     * This method shows main page
//     * @param model an object of type model
//     * @param authentication an object of type Authentication
//     * @return index page
//     */

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