package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProgramRepository extends JpaRepository<Program, Long> {


    Set<Program> findProgramByDepartmentId(Long departmentId);

}
