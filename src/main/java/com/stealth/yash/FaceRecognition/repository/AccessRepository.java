package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.AccessKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AccessRepository extends JpaRepository<AccessKey,Long> {


}
