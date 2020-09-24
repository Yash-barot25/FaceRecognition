package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Course;

import java.util.Set;

public interface CourseService extends CrudService<Course, Long> {

    Set<Course> findCoursesByProgramId(Long id);

    Set<Course> findCoursesByProfessorId(Long id);

}
