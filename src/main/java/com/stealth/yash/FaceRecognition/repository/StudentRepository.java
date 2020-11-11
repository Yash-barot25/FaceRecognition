/**
 * ************************* FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Student
 * An interface with name StudentRepository that extends JpaRepository class
 * Two parameters are passed.
 * Student represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // query method for finding student by Last name.
    Student findByLastName(String lastName);
    // query method for finding list of student by Last name.
    List<Student> findAllByLastNameLike(String lastName);
    // query method for finding student by image
    Student findByImage(String image);
    // query for finding Student by email
    Student findByEmail(String email);

    @Query(value = "select  s from Student s where s.firstName like %:val% or  s.lastName like %:val% or s.email like %:val% or s.phoneNumber like %:val% or s.id = :val")
    List<Student> searchStudent(@Param("val") String value);

    Student findStudentByAccessKey_Id(Long id);

    Student findFirstByFirstName(String name);




}
