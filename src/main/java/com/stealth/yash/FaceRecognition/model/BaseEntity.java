/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - BaseEntity
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//Put on any field to make lombok build a standard getter
@Getter
//Put on any field to make lombok build a standard setter
@Setter
//Will generate an error message if such a constructor cannot be written due to the existence of final fields.
@NoArgsConstructor
//Generates an all-args constructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    //Specifies the primary key
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }
}