/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model -Student
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
import javax.validation.constraints.*;
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
//Specifies the primary table for student
@Table(name = "student")

public class Student{

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "student_id")
    private Long id;

    //Specifies mapped column
    @Column(name = "image")
    private String image;

    // ensures first_name must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "first name can't be blank")
    //Specifies mapped column
    @Column(name = "first_name")
    private String firstName;

    // ensures lastName must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "last name can't be blank")
    //Specifies mapped column
    @Column(name = "last_name")
    private String lastName;

    // ensures email must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    //Specifies mapped column
    @Column(name = "student_email", unique = true)
    private String email;

    // ensures contactNumber must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Contact-Number can't be blank")
    //ensure size must be between the specified boundaries
    @Size(min = 10, max = 12, message
            = "Contact-Number must be between 10 and 12 Digits")
    //Specifies mapped column
    @Column(name = "student_contact_number")
    private String phoneNumber;

    // ensures address must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Address can't be blank")
    //ensure size must be between the specified boundaries
    @Size(min = 5, max = 50, message
            = "Address must be between 5 and 50 characters long")
    //Specifies mapped column
    @Column(name = "student_address")
    private String address;


    // currentSemester must not be null. Accepts any type
    @NotNull(message = "Current-semester can't be blank")
    // specifies currentsemester must be a number whose value must be higher or equal to the 1
    @Min(value = 1, message = "Current-Semester can't be less than 1")
    // specifies currentsemester must be a number whose value must be lower or equal to the 8
    @Max(value = 8,  message = "Current-Semester can't be more than 8")
    //Specifies mapped column
    @Column(name = "student_current_semester")
    private Long currentSemester;
    @Column(name = "face_id_aws")
    private String faceIdAWS;

    // Specifies that campus should be persisted as a enumerated type
    @Enumerated(value = EnumType.STRING)
    private Campus campus;


    // GPA must not be null. Accepts any type
    @NotNull(message = "GPA can't be blank. DID YOU FAILED...?")
   // specifies GPA must be a number whose value must be higher or equal to the 1
    @Min(value = 0, message = "GPA can't be less than 0")
    /// specifies GPA must be a number whose value must be higher or equal to the 1
    @Max(value = 4,  message = "GPA can't be more than 4")
    //Specifies mapped column
    @Column(name = "student_GPA")
    private Double GPA;

    @JsonIgnore
    // Program must not be null. Accepts any type
    @NotNull(message = "You Must Select A Program")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore

    // Department selected  must not be null. Accepts any type
    @NotNull(message = "You Must Select A Department")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "department_id")
    private Department department;

    //Specifies mapped column
    @Column(name = "stud_pass_email")
    private String stuPasswordEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stud_access_id", referencedColumnName = "access_Id")
    private AccessKey accessKey;


    /**
     * @param o object of type Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    /**
     *getter method for Id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *setter method for Id
     *  @param id an object of type Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    public char getFirstLetterOfFirstName() {return this.firstName.charAt(0);}
}
