/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Professor
 * An interface with name ProfessorRepository that extends JpaRepository class
 * Two parameters are passed.
 * Professor represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

//start interface
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // query method for finding professors by programID
    Set<Professor> findProfessorsByProgramId(Long id);
    // query method for finding professors by departmentID
    Set<Professor> findProfessorsByDepartmentId(Long id);
}
//end interface
