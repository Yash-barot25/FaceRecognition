/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.service.springdatajpa;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import com.stealth.yash.FaceRecognition.model.Course;
import com.stealth.yash.FaceRecognition.repository.CourseRepository;
import com.stealth.yash.FaceRecognition.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//indicates that this class is a service
@Service
public class CourseSDJpaService implements CourseService {

    // declaring a courseRepository
    private final CourseRepository courseRepository;

    /** constructor
     * @param courseRepository repository
     */
    public CourseSDJpaService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**
     * find all courses from repository
     * @return set containing courses
     */
    @Override
    public Set<Course> findAll() {
        return new HashSet<>(courseRepository.findAll());
    }

    /**
     * find courses by indicated courseID else throw exception
     * @param aLong parameter of type Long
     * @return courses that may or may not exist
     */
    @Override
    public Course findById(Long aLong) {
        Optional<Course> courseOptional = courseRepository.findById(aLong);

        if (courseOptional.isPresent()){
            return courseOptional.get();
        }
        throw new NotFoundException("Course ID: " + aLong + " Was Not Found.");
    }

    /**
     * Saving course to the repository
     * @param object a course object
     * @return the saved course
     */
    @Override
    public Course save(Course object) {
        return courseRepository.save(object);
    }


    /**
     * delete indicated course from the repository
     * @param object course object
     *
     */
    @Override
    public void delete(Course object) {
        courseRepository.delete(object);
    }


    /**
     * delete course indicated by courseId  from the repository
     * @param aLong object of type long
     *
     */
    @Override
    public void deleteById(Long aLong) {
        courseRepository.deleteById(aLong);

    }

    /**
     * find courses by indicated progamID
     * @param id parameter of type Long
     * @return courses
     */
    @Override
    public Set<Course> findCoursesByProgramId(Long id) {
        return courseRepository.findCoursesByProgramId(id);
    }


    /**
     * find courses by indicated professorID
     * @param id parameter of type Long
     * @return courses
     */
    @Override
    public Set<Course> findCoursesByProfessorId(Long id) {
        return courseRepository.findCoursesByProfessorId(id);
    }

}
