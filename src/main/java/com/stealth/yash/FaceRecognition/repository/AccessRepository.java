package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.AccessKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AccessRepository extends JpaRepository<AccessKey,Long> {

    @Query(value = "Select  a.access_id,a.access_fob_id  from accesskey a where a.access_id NOT IN (Select  s.stud_access_id  from student s inner join accesskey a on s.stud_access_id =a.access_id )",nativeQuery = true)
    List<AccessKey> findAccessFobs();
}
