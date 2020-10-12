package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByLastName(String lastName);

    List<Student> findAllByLastNameLike(String lastName);

    Student findByImage(String image);

    @Query(value = "Select s.accessKey.accessfobid from Student s")
    List<String> findAccessFobIds();

    @Query(value = "select  s from Student s where s.firstName like %:val% or  s.lastName like %:val% or s.email like %:val% or s.phoneNumber like %:val% or s.id = :val")
    List<Student> searchStudent(@Param("val") String value);


}
