package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String name);
    List<Department> findAllByDepartmentNameLike(String name);
    Department findByDescription(String name);

    @Query(value = "select  d from Department d where d.departmentName like %:val% or  d.description like %:val%  or d.id = :val")
    List<Department> searchDepartment(@Param("val") String value);
}
