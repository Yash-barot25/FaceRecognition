package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long id;

    @Lob
    @Column(name = "image")
    private String imageURL;

    @NotBlank(message = "first name can't be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Enter your experience")
    @Column(name = "professor_experience")
    private Double experience;

    @NotNull(message = "Enter your projects")
    @Column(name = "professor_projects")
    private Long projects;

    @NotBlank(message = "Email ID please!")
    @Email
    @Column(name = "professor_email")
    private String email;

    @NotBlank(message = "Phone number can't be blank")
    @Size(min = 10, max = 12, message = "Number must be between 10 to 12 numbers long")
    @Column(name = "professor_contact_number")
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    @Enumerated(value = EnumType.STRING)
    private Level level;

    @Enumerated(value = EnumType.STRING)
    private Availibility availibility;

    @Lob
    @Size(min = 5, max = 150, message = "Enter your bio")
    @Column(name = "professor_bio")
    private String bio;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
//    private Set<Experty> experties;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "professor")
    private Set<Course> courses;


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
