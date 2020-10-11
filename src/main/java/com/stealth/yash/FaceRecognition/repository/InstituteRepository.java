/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Institute
 * An interface with name InstituteRepository that extends JpaRepository class
 * Two parameters are passed.
 * Institute represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

//start interface
public interface InstituteRepository extends JpaRepository<Institute, Long> {

}
//ends
