package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService extends CrudService<Student, Long> {

    Student findByLastName(String lastName);

    List<Student> findAllByLastNameLike(String lastName);

    Page<Student> findPaginated(int pageNo, int pageSize);
}
