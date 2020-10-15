package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    Set<Program> findProgramByDepartmentId(Long departmentId);
    Program findByProgramName(String name);
    List<Program> findAllByProgramNameLike(String name);

    @Query(value = "select  p from Program p where p.programName like %:val% or p.id = :val")
    List<Program> searchProgram(@Param("val") String value);
}
