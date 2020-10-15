package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Department;

import java.util.List;

public interface DepartmentService extends CrudService<Department, Long> {

    Department findByDepartmentName(String name);
    List<Department> findAllByDepartmentNameLike(String name);
    List<Department> searchDepartment(String value);
    Department findByDescription(String name);
}
