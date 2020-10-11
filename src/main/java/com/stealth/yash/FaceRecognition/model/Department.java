/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Department
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
//Specifies the primary table for department
@Table(name = "department")

public class Department {

    //Specifies the primary key
    @Id
    //Provides for the specification of generation strategies for the values of primary keys
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "department_id")
    private Long id;

    // ensure that element must not be null
    @NotBlank(message = "Department name can't be blank")
    //Specifies mapped column
    @Column(name = "department_name")
    private String departmentName;

    // ensure that element must not be null
    @NotBlank(message = "Description can't be blank")
    //Specifies mapped column
    @Column(name = "department_description")
    private String description;

    @JsonIgnore
    //specifies a single-valued association to another entity class that has many-to-one multiplicity
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @JsonIgnore
    // Specifies a many-valued association with one-to-many multiplicity
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Program> programs;

    @JsonIgnore
    //Specifies a many-valued association with one-to-many multiplicity.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Professor> professors;

    @JsonIgnore
    //Specifies a many-valued association with one-to-many multiplicity.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Course> courses;

    @JsonIgnore
    //Specifies a many-valued association with one-to-many multiplicity.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Student> students;


    /**
     * @param o an object of type Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId().equals(that.getId());
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param institute an object of type institute
     * @return boolean value
     */
    public void setInstitute(Institute institute) {
        this.institute = institute;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     *This method gets Id
     * @return id
     */
    public Long getId() {
        return id;
    }
}
