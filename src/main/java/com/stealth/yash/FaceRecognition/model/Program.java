package com.stealth.yash.FaceRecognition.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "program")
public class Program {

    @Id
    @NotNull
    @Column(name = "program_code")
    private String id;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "num_of_semester")
    private Integer numberOfSemester;

    @Lob
    @Column(name = "program_description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
            , mappedBy = "programs")
    private Set<Professor> professors;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "program")
    private Set<Student> students;

    @ManyToMany(cascade = CascadeType.ALL
            , mappedBy = "programs")
    private Set<Course> courses;
}
