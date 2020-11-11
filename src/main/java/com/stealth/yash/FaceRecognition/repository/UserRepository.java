/**
 * ************************* FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - User
 *
 * An interface with name UserRepository that extends JpaRepository class
 * Two parameters are passed.
 * User represents the type of entity it manages
 * Long represents type of the ID field
 *
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUseremail(String userEmail);

    Optional<User> deleteUserByUseremail(String userEmail);

}
