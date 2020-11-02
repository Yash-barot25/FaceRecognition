/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Course
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
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
//Specifies the primary table for course
@Table(name = "course")


public class Course {

    //Specifies the primary key
    @Id
    //Provides for the specification of generation strategies for the values of primary keys
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "course_code")
    private Long id;

    // ensure that element must not be null
    @NotBlank(message = "Course name can't be blank")
    //Specifies mapped column
    @Column(name = "course_name")
    private String courseName;

    // ensure that element must not be null
    @NotNull(message = "Enter your credits!")
    //Specifies mapped column
    @Column(name = "course_credit")
    private Double credit;

    // ensure that element must not be null
    @NotNull(message = "Enter your semester!")
    //Specifies mapped column
    @Column(name = "course_offered_in_semester")
    private Integer semester;

    // Specifies that campus should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    // Ensures that field should be persisted as a large object to a database-supported large object type
    @Lob
    // ensures course_description must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Enter the course description!")
    //Specifies mapped column
    @Column(name = "course_description")
    private String description;


    @JsonIgnore
    //specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "course_id")
    private Professor professor;

    @JsonIgnore
    //specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore
    //specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "department_id")
    private Department department;


    /**
     * @param o object of type Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
