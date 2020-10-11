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

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // query method for finding student by Last name.
    Student findByLastName(String lastName);
    // query method for finding list of student by Last name.
    List<Student> findAllByLastNameLike(String lastName);
    // query method for finding student by image
    Student findByImage(String image);


}
