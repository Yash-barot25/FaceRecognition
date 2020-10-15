package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Course;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CourseService extends CrudService<Course, Long> {

    Set<Course> findCoursesByProgramId(Long id);
    Set<Course> findCoursesByProfessorId(Long id);

    Course findByCourseName(String name);
    List<Course> findAllByCourseNameLike(String name);
    Course findByDescription(String description);
    List<Course> findAllByDescriptionLike(String description);

    List<Course> searchCourse(String value);
}
