/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 *
 *  @author  STEALTH
 *
 CourserService interface.
 * @param <Course> to manage the course entity
 * @param <Long> Resource id type, usually Long or String
 *
 */
package com.stealth.yash.FaceRecognition.service;

import com.stealth.yash.FaceRecognition.model.Course;

import java.util.Set;

public interface CourseService extends CrudService<Course, Long> {

    // query method for finding courses  by  ProgramID
    Set<Course> findCoursesByProgramId(Long id);
    // query method for finding course by  ProfessorID
    Set<Course> findCoursesByProfessorId(Long id);

}
