/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
StudentService interface extending CRUDService
 * @param <Student> to manage the professor entity
 * @param <Long> Resource id type
 *
 */
package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService extends CrudService<Student, Long> {

    // query method for finding student by Last name.
    Student findByLastName(String lastName);
    // query method for finding list of student by Last name.
    List<Student> findAllByLastNameLike(String lastName);
    // query method for finding student by image
    Page<Student> findPaginated(int pageNo, int pageSize);
}
