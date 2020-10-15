package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.repository.CourseRepository;
import com.stealth.yash.FaceRecognition.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseSDJpaService implements CourseService {

    private final CourseRepository courseRepository;

    public CourseSDJpaService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course findByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }

    @Override
    public List<Course> findAllByCourseNameLike(String name) {
        return courseRepository.findAllByCourseNameLike(name);
    }

    @Override
    public Course findByDescription(String value) {
        return courseRepository.findByDescription(value);
    }

    @Override
    public List<Course> findAllByDescriptionLike(String description) {
        return courseRepository.findAllByDescriptionLike(description);
    }

    @Override
    public List<Course> searchCourse(String value) {
        return courseRepository.searchCourse(value);
    }

    @Override
    public Set<Course> findAll() {
        return new HashSet<>(courseRepository.findAll());
    }

    @Override
    public Course findById(Long aLong) {
        Optional<Course> courseOptional = courseRepository.findById(aLong);

        if (courseOptional.isPresent()){
            return courseOptional.get();
        }
        throw new NotFoundException("Course ID: " + aLong + " Was Not Found.");
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
