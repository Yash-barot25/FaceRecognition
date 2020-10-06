///*
//package com.stealth.yash.FaceRecognition.service;
//
//import com.stealth.yash.FaceRecognition.model.Student;
//import com.stealth.yash.FaceRecognition.repository.StudentRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class ImageServiceImpl implements ImageService {
//
//
//    private final StudentRepository studentRepository;
//
//
//    public ImageServiceImpl(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @Override
//    @Transactional
//    public void saveImageFile(Long recipeId, MultipartFile file) {
//
//        try {
//            Optional<Student>  studentOptional = studentRepository.findById(recipeId);
//
//          //  if(studentOptional.isPresent()){
//                Student student = studentOptional.get();
//                Byte[] byteObjects = new Byte[file.getBytes().length];
//                int i = 0;
//                for (byte b : file.getBytes()){
//                    byteObjects[i++] = b;
//                }
//                student.setImage(byteObjects);
//                studentRepository.save(student);
//           // }else {
//               // throw new RuntimeException("Invalid Account Accessed");
//           // }
//
//
//        } catch (IOException e) {
//            //todo handle better
////            log.error("Error occurred", e);
//
//            e.printStackTrace();
//        }
//    }
//}
//*/
