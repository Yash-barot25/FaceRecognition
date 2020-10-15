package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Professor;

import java.util.List;
import java.util.Set;

public interface ProfessorService extends CrudService<Professor, Long> {

    Set<Professor> findProfessorsByProgramId(Long id);
    Set<Professor> findProfessorsByDepartmentId(Long id);
    Professor findByFirstName(String name);
    List<Professor> findAllByFirstNameLike(String firstname);
    Professor findByLastName(String name);
    List<Professor> findAllByLastNameLike(String lastname);
    Professor findByEmail(String email);
    Professor findByPhoneNumber(String number);
    List<Professor> searchProfessor(String value);
}
