package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//Put on any field to make lombok build a standard getter.
@Getter
//Put on any field to make lombok build a standard setter.
@Setter
//Generates a no-args constructor
@NoArgsConstructor
//Generates an all-args constructor
@AllArgsConstructor
//Specifies that this class is an entity
@Entity
public class LogUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    private Long id;
    @Column(name = "user_fob_id")
    private String userFobId;
    @Column(name="accessed_time")
    private String accessTime;


}
