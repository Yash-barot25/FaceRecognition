package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.repository.CourseRepository;
import com.stealth.yash.FaceRecognition.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseSDJpaService implements CourseService {

    private final CourseRepository courseRepository;

    public CourseSDJpaService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Set<Course> findAll() {
        return new HashSet<>(courseRepository.findAll());
    }

    @Override
    public Course findById(Long aLong) {
        return courseRepository.findById(aLong).orElse(null);
    }

    @Override
    public Course save(Course object) {
        return courseRepository.save(object);
    }

    @Override
    public void delete(Course object) {
        courseRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        courseRepository.deleteById(aLong);

    }

    @Override
    public Set<Course> findCoursesByProgramId(Long id) {
        return courseRepository.findCoursesByProgramId(id);
    }

    @Override
    public Set<Course> findCoursesByProfessorId(Long id) {
        return courseRepository.findCoursesByProfessorId(id);
    }

}
