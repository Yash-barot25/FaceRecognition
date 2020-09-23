package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.model.Student;
import com.stealth.yash.FaceRecognition.service.ImageService;
import com.stealth.yash.FaceRecognition.service.springdatajpa.StudentSDJpaService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/upload")
public class ImageController {

    private final ImageService imageService;
    private final StudentSDJpaService studentService;

    public ImageController(ImageService imageService, StudentSDJpaService studentService) {
        this.imageService = imageService;
        this.studentService = studentService;
    }

    @GetMapping("/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", studentService.findById(id));

        return "/imageuploadform";
    }

    @PostMapping("/{id}")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(id, file);

        return "redirect:/students/get/" + id;
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Student student = studentService.findById(id);

        if (student.getImage() != null) {
            byte[] byteArray = new byte[student.getImage().length];
            int i = 0;

            for (Byte wrappedByte : student.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
