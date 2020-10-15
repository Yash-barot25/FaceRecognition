package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Department;
import com.stealth.yash.FaceRecognition.model.Program;

import java.util.List;
import java.util.Set;

public interface ProgramService extends CrudService<Program, Long> {

    Set<Program> findProgramByDepartmentId(Long departmentId);
    Program findByProgramName(String name);
    List<Program> findAllByProgramNameLike(String name);
    List<Program> searchProgram(String value);
}
