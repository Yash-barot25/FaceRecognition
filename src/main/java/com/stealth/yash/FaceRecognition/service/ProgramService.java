/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
ProgramService interface extending CRUDService
 * @param <Program> to manage the professor entity
 * @param <Long> Resource id type
 *
 */
package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Program;

import java.util.Set;

public interface ProgramService extends CrudService<Program, Long> {

    //query method for finding program by departmentID
    Set<Program> findProgramByDepartmentId(Long departmentId);

}
