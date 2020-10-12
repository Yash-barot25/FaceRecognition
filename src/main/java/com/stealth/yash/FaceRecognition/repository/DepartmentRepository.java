/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Department
 * An interface with name DepartmentRepository that extends JpaRepository class
 * Two parameters are passed.
 * Department represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface start
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}//ends
