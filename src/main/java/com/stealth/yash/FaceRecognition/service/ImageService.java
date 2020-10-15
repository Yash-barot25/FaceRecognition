/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
ImageService interface
 * Saves images of a studnet in the form of MutlipartFile
 *
 */
package com.stealth.yash.FaceRecognition.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     *saveImageFile method
     * saves images of students
     * @param studentId studnetId to type Long
     * @param file imageFile of type Multipart
     */
    void saveImageFile(Long studentId, MultipartFile file);
}