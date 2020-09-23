package com.stealth.yash.FaceRecognition.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long studentId, MultipartFile file);
}