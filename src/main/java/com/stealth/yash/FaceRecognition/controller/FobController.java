package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.AccessKey;
import com.stealth.yash.FaceRecognition.service.springdatajpa.AccessSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.DepartmentSDJpaService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.InstituteSDJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FobController {

    private final AccessSDJpaService accessSDJpaService;

    public FobController(AccessSDJpaService accessSDJpaService) {
        this.accessSDJpaService = accessSDJpaService;

    }

    @GetMapping("/fob")
    public String accessPage(Model model){
        model.addAttribute("fobaccess",accessSDJpaService.findAll());
        model.addAttribute("accesskey",new AccessKey());
        return "fobaccess/fobaccess";
    }



    @PostMapping("/addfob")
    public String addKey(@Valid @ModelAttribute("accesskey")AccessKey accesskey,Model model, String accessfobid){
        accesskey.setAccessfobid(accessfobid);
        accessSDJpaService.save(accesskey);
        model.addAttribute("accesskey",accesskey);
        model.addAttribute("fobaccess",accessSDJpaService.findAll());
        return "fobaccess/fobaccess";

    }
}
