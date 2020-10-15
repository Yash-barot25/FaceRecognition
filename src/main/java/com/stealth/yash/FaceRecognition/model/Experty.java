/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Experty
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
//Specifies the primary table for experties
@Table(name = "experties")
public class Experty {

    //Specifies id the primary key
    @Id
    //Specified id should not be null
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //specifies name field should be persisted as a large object to a database-supported large object type
    @Lob
    //Specifies the mapped column
    @Column(name = "experty")
    private String name;

    ///specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne()
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
