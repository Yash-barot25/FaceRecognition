package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Program;

import java.util.Set;

public interface ProgramService extends CrudService<Program, Long> {

    Set<Program> findProgramByDepartmentId(Long departmentId);

}
