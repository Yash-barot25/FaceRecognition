package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
    public User findByUseremail(String useremail);
}
