/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Program
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stealth.yash.FaceRecognition.enums.Campus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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
//Specifies the primary table for program
@Table(name = "program")


public class Program {

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "program_code")
    private Long id;

    //Specifies mapped column
    @Column(name = "program_length")
    private Long length;

    //Specifies mapped column
    @Column(name = "program_rating")
    private Long rating;

    //Specifies mapped column
    @Column(name = "program_name")
    private String programName;

    @Column(name = "num_of_semester")
    private Integer numberOfSemester;

    @Lob
    //Specifies mapped column
    @Column(name = "program_description")
    private String description;

    // Specifies that campus should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Campus campus;


    @JsonIgnore
    //specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    // Specifies a many-valued association with one-to-many multiplicity
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "program")
    private Set<Professor> professors;

    @JsonIgnore
    // Specifies a many-valued association with one-to-many multiplicity
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "program")
    private Set<Student> students;

    @JsonIgnore
    // Specifies a many-valued association with one-to-many multiplicity
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
            , mappedBy = "program")
    private Set<Course> courses;


    /**
     * @param o object of type Object
     * @return boolean value
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program)) return false;
        Program program = (Program) o;
        return Objects.equals(getId(), program.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
