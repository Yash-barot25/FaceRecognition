package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Lob
    @Column(name = "image")
    private Byte[] image;


    @NotBlank(message = "first name can't be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    @Column(name = "student_email", unique = true)
    private String email;

    @NotBlank(message = "Contact-Number can't be blank")
    @Size(min = 10, max = 15, message
            = "Contact-Number must be between 10 and 15 Digits")
    @Column(name = "student_contact_number")
    private String phoneNumber;

    @NotBlank(message = "Address can't be blank")
    @Size(min = 5, max = 50, message
            = "Address must be between 5 and 50 characters long")
    @Column(name = "student_address")
    private String address;

    @NotNull(message = "Current-semester can't be blank")
    @Min(value = 1, message = "Current-Semester can't be less than 1")
    @Max(value = 8,  message = "Current-Semester can't be more than 8")
    @Column(name = "student_current_semester")
    private Long currentSemester;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    @NotNull(message = "GPA can't be blank. DID YOU FAILED...?")
    @Min(value = 0, message = "GPA can't be less than 0")
    @Max(value = 4,  message = "GPA can't be more than 4")
    @Column(name = "student_GPA")
    private Double GPA;

    @JsonIgnore
    @NotNull(message = "You Must Select A Program")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore
    @NotNull(message = "You Must Select A Department")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "department_id")
    private Department department;

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


}
