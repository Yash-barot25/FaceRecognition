package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Set<Course> findCoursesByProgramId(Long id);

    Set<Course> findCoursesByProfessorId(Long id);



}
