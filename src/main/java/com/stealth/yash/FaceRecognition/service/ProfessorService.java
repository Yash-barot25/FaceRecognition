/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
ProfessorService interface extending CRUDService
 * @param <Professor> to manage the professor entity
 * @param <Long> Resource id type
 *
 */
package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Professor;

import java.util.Set;

public interface ProfessorService extends CrudService<Professor, Long> {
    //query method for finding professor by programID
    Set<Professor> findProfessorsByProgramId(Long id);
    //query method for finding professor by departmentID
    Set<Professor> findProfessorsByDepartmentId(Long id);

}
