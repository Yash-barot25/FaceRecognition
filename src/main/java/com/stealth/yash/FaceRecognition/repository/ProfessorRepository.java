package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Set<Professor> findProfessorsByProgramId(Long id);

    Set<Professor> findProfessorsByDepartmentId(Long id);
}
