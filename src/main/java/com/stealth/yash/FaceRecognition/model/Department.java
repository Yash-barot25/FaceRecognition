package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    private String departmentName;


    @Column(name = "department_description")
    private String description;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Program> programs;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Professor> professors;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Course> courses;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Student> students;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId().equals(that.getId());
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
