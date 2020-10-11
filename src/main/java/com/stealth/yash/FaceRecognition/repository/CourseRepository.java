/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Course
 * An interface with name CourseRepository that extends JpaRepository class
 * Two parameters are passed.
 * Course represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


// Interface starts
public interface CourseRepository extends JpaRepository<Course, Long> {

    // query method for finding by  ProgramID
    Set<Course> findCoursesByProgramId(Long id);
    // query method for finding by ProfessorIDID
    Set<Course> findCoursesByProfessorId(Long id);



}//ends