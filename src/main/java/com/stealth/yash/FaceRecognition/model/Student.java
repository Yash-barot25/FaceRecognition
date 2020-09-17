package com.stealth.yash.FaceRecognition.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @NotNull
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_contact_number")
    private String phoneNumber;

    @Column(name = "student_address")
    private String address;

    @Column(name = "student_current_semester")
    private Long currentSemester;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    @Column(name = "student_GPA")
    private Double GPA;

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private Set<Course> courses;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "program_id")
    private Program program;

}
