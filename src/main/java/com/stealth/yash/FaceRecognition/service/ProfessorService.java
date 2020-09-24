package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Professor;

import java.util.Set;

public interface ProfessorService extends CrudService<Professor, Long> {
    Set<Professor> findProfessorsByProgramId(Long id);

    Set<Professor> findProfessorsByDepartmentId(Long id);

}
