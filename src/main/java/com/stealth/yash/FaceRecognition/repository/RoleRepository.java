/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Repository - Role
 * An interface with name RoleRepository that extends JpaRepository class
 * Two parameters are passed.
 * Role represents the type of entity it manages
 * Long represents type of the ID field
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    //query method for finding role by rolename
    public Role findByRolename(String rolename);
}
