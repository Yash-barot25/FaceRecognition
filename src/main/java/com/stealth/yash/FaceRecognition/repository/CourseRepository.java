package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Set<Course> findCoursesByProgramId(Long id);
    Set<Course> findCoursesByProfessorId(Long id);

    Course findByCourseName(String name);
    List<Course> findAllByCourseNameLike(String name);
    Course findByDescription(String description);
    List<Course> findAllByDescriptionLike(String description);

    @Query(value = "select  c from Course c where c.courseName like %:val% or  c.description like %:val% or c.id = :val")
    List<Course> searchCourse(@Param("val") String value);
}
