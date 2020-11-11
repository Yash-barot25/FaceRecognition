package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.LogUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LogUsersRepository extends JpaRepository<LogUsers, Long> {

//
//    @Query(value = "Select accessTime from LogUsers group by accessTime")
//    List<String> findData();
//
//    @Query(value = "Select count(distinct userFobId) as TotalUsers from LogUsers  group by accessTime")
//    List<String> findCount();

    Long countAllByUserFobId(String fobID);

    Long countAllByAccessDate(LocalDate localDate);

    @Query("Select distinct accessDate from LogUsers" )
    List<LocalDate>  getValuesOfDistinctDates();

}