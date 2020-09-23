package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Program> programs;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Professor> professors;


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
