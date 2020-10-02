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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_code")
    private Long id;

    @NotBlank(message = "Institute name can't be blank")
    @Size(min = 5, max = 50, message = "Address must be between 5 and 50 characters long")
    @Column(name = "institute_name")
    private String name;

    @Lob
    @NotBlank(message = "Address can't be blank")
    @Size(min = 5, max = 150, message = "Address must be between 5 and 150 characters long")
    @Column(name = "institute_address")
    private String address;

    @Email
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    @Column(name = "institute_email")
    private String email;

    @Lob
    @NotBlank(message = "Description can't be blank")
    @Size(min = 5, max = 1000, message = "Address must be between 5 and 1000 characters long")
    @Column(name = "institute_description")
    private String description;

    @Column(name = "institute_contact_number")
    @Size(min = 10, max = 12, message = "Contact-Number must be between 10 and 12 Digits")
    @NotBlank(message = "Contact number can't be blank")
    private String contactNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
    Set<Department> departments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institute)) return false;
        Institute institute = (Institute) o;
        return getId().equals(institute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
