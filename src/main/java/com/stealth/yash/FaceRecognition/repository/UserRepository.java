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

public interface UserRepository extends JpaRepository<User,Long> {

    // query method for finding user by username
    public User findByUsername(String username);
    // query method for finding user by user email
    public User findByUseremail(String useremail);
}
