/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Program
 * An interface with name ProgramRepository that extends JpaRepository class
 * Two parameters are passed.
 * Program represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

//start interface
public interface ProgramRepository extends JpaRepository<Program, Long> {

    // query method for finding program by departmentID
    Set<Program> findProgramByDepartmentId(Long departmentId);

}// interface ends