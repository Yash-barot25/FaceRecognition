package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstituteRepository extends JpaRepository<Institute, Long> {

    Institute findByName(String name);

    List<Institute> findAllByNameLike(String name);

    @Query(value = "select  i from Institute i where i.name like %:val% or  i.email like %:val% or i.contactNumber like %:val% or i.address like %:val% or i.id = :val")
    List<Institute> searchInstitute(@Param("val") String value);
}
