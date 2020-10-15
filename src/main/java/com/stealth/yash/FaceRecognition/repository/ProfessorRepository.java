package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Set<Professor> findProfessorsByProgramId(Long id);
    Set<Professor> findProfessorsByDepartmentId(Long id);
    Professor findByFirstName(String name);
    List<Professor> findAllByFirstNameLike(String firstname);
    Professor findByLastName(String name);
    List<Professor> findAllByLastNameLike(String lastname);
    Professor findByEmail(String email);
    Professor findByPhoneNumber(String number);

    @Query(value = "select  p from Professor p where p.firstName like %:val% or  p.lastName like %:val% or p.email like %:val% or p.phoneNumber like %:val% or p.id = :val")
    List<Professor> searchProgram(@Param("val") String value);
}
