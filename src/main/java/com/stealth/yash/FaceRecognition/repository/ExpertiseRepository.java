/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Expertise
 * An interface with name ExpertiseRepository that extends JpaRepository class
 * Two parameters are passed.
 * Experty represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Experty;
import org.springframework.data.jpa.repository.JpaRepository;

//starts
public interface ExpertiseRepository extends JpaRepository<Experty, Long> {

}//ends
