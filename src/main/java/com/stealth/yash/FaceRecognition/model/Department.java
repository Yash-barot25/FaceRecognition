package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @NotNull
    @Column(name = "department_id")
    private Long departmentID;

    @Column(name = "department_name")
    private String name;

    @Column(name = "department_description")
    private String description;

    @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "institute_id")
    private Institute institute;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Program> programs;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Professor> professors;



}
