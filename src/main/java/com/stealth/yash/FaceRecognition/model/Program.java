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
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_code")
    private Long id;

    @Column(name = "program_length")
    private Long length;

    @Column(name = "program_rating")
    private Long rating;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "num_of_semester")
    private Integer numberOfSemester;

    @Lob
    @Column(name = "program_description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "program")
    private Set<Professor> professors;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "program")
    private Set<Student> students;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
            , mappedBy = "program")
    private Set<Course> courses;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program)) return false;
        Program program = (Program) o;
        return Objects.equals(getId(), program.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
