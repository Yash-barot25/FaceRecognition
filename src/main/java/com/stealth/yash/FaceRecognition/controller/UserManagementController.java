package com.stealth.yash.FaceRecognition.controller;

import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.stealth.yash.FaceRecognition.model.User;
import com.stealth.yash.FaceRecognition.repository.RoleRepository;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/usermanagement")
public class UserManagementController {

    private

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserManagementController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public String userManagementPage(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "manage/usermanagement";
    }

    @GetMapping("/disable/{id}")
    public String disable(@PathVariable("id") Long userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with  Not found"));
        user.setEnabled(false);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "manage/usermanagement";
    }

    @GetMapping("/enable/{id}")
    public String enable(@PathVariable("id") Long userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with  Not found"));
        user.setEnabled(true);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "manage/usermanagement";
    }
}
