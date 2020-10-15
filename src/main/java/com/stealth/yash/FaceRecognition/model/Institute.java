/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Institute
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
//Specifies the primary table for institute
@Table(name = "institute")


public class Institute {

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Specifies mapped column
    @Column(name = "institute_code")
    private Long id;

    //Ensures name cannot be blank
    @NotBlank(message = "Institute name can't be blank")
    @Size(min = 5, max = 50, message = "Address must be between 5 and 50 characters long")
    //Specifies mapped column
    @Column(name = "institute_name")
    private String name;

    // Ensures that address should be persisted as a large object to a database-supported large object type
    @Lob
    //Ensures address cannot be blank
    @NotBlank(message = "Address can't be blank")
    @Size(min = 5, max = 150, message = "Address must be between 5 and 150 characters long")
    //Specifies mapped column
    @Column(name = "institute_address")
    private String address;

    @Email
    //Ensures email cannot be blank
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    //Specifies mapped column
    @Column(name = "institute_email")
    private String email;

    // Ensures that description should be persisted as a large object to a database-supported large object type
    @Lob
    //Ensures decription cannot be blank
    @NotBlank(message = "Description can't be blank")
    @Size(min = 5, max = 1000, message = "Address must be between 5 and 1000 characters long")
    //Specifies mapped column
    @Column(name = "institute_description")
    private String description;

    //Specifies mapped column
    @Column(name = "institute_contact_number")
    //ensure size of contact number must be between the boundaries
    @Size(min = 10, max = 12, message = "Contact-Number must be between 10 and 12 Digits")
    //Ensures contact number cannot be blank
    @NotBlank(message = "Contact number can't be blank")
    private String contactNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
    Set<Department> departments;



    /**
     * @param o
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institute)) return false;
        Institute institute = (Institute) o;
        return getId().equals(institute.getId());
    }


    /**
     * @return Id
     */
    public Long getId() {
        return id;
    }


    /**
     * @param id an object of type Long
     * sets Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
