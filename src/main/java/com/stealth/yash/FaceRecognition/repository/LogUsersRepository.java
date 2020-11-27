package com.stealth.yash.FaceRecognition.repository;

import com.stealth.yash.FaceRecognition.model.LogUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "Select userFobId from LogUsers where accessDate = :val")
    List<String> getStudents(@Param("val") LocalDate value);

    @Query("Select distinct accessDate from LogUsers" )
    List<LocalDate>  getValuesOfDistinctDates();

    List<LogUsers> findAllByAccessDate(LocalDate localDate);

}