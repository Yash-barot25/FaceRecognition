package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByLastName(String lastName);

    List<Student> findAllByLastNameLike(String lastName);

    Student findByImage(String image);

    @Query("Select s.accessKey.accessfobid from Student s")
    List<String> findAccessFobIds();


}
