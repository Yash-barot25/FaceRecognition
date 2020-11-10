/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Professor
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stealth.yash.FaceRecognition.enums.Availibility;
import com.stealth.yash.FaceRecognition.enums.Campus;
import com.stealth.yash.FaceRecognition.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
//Specifies the primary table for professor
@Table(name = "professor")



public class Professor {

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "professor_id")
    private Long id;

    // Ensures that field should be persisted as a large object to a database-supported large object type
    @Lob
    //Specifies mapped column
    @Column(name = "image")
    private String imageURL;

    // ensures first_name must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "first name can't be blank")
    //Specifies mapped column
    @Column(name = "first_name")
    private String firstName;

    // ensures last_name must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "last name can't be blank")
    //Specifies mapped column
    @Column(name = "last_name")
    private String lastName;

    // eperience must not be null. Accepts any type
    @NotNull(message = "Enter your experience")
    //Specifies mapped column
    @Column(name = "professor_experience")
    private Double experience;


    // projects must not be null. Accepts any type
    @NotNull(message = "Enter your projects")
    //Specifies mapped column
    @Column(name = "professor_projects")
    private Long projects;

    // ensures email must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Email ID please!")
    @Email
    //Specifies mapped column
    @Column(name = "professor_email")
    private String email;

    // ensures phoneNumber must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Phone number can't be blank")
    //ensure size must be between the specified boundaries
    @Size(min = 10, max = 12, message = "Number must be between 10 to 12 numbers long")
    //Specifies mapped column
    @Column(name = "professor_contact_number")
    private String phoneNumber;

    // Specifies that campus should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    // Specifies that level should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Level level;

    // Specifies that availibility should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Availibility availibility;

    // Ensures that field should be persisted as a large object to a database-supported large object type
    @Lob
    //ensure size must be between the specified boundaries
    @Size(min = 5, max = 150, message = "Enter your bio")
    //Specifies mapped column
    @Column(name = "professor_bio")
    private String bio;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
//    private Set<Experty> experties;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    //Specifies a column for joining an entity association or element collection
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore
    // Specifies a many-valued association with one-to-many multiplicity
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "professor")
    private Set<Course> courses;

    /**
     * @param o object of type Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return getId().equals(professor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
