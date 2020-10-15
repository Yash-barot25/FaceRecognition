package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Institute;
import com.stealth.yash.FaceRecognition.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstituteService extends CrudService<Institute, Long> {

    Institute findByName(String name);
    List<Institute> findAllByNameLike(String name);
    List<Institute> searchInstitute(String value);
}
