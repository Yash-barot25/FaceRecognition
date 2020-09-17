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
@Table(name = "course")
public class Course {

    @Id
    @NotNull
    @Column(name = "course_code")
    private String id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_credit")
    private Double credit;

    @Column(name = "course_offered_in_semester")
    private Integer semester;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    @Lob
    @Column(name = "course_description")
    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
            , mappedBy = "courses")
    private Set<Professor> professors;

    @OneToOne
    @JoinColumn(name = "coordinator_id")
    private Professor coordinator;

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
//            , mappedBy = "courses")
//    private Set<Student> students;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "program_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id"))
    private Set<Program> programs;


   

}
