package com.stealth.yash.FaceRecognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Program length can't be blank")
    @Column(name = "program_length")
    private Long length;

    @NotNull(message = "Ratings can't be blank")
    @Column(name = "program_rating")
    private Long rating;

    @NotBlank(message = "Program name can't be blank")
    @Column(name = "program_name")
    private String programName;

    @NotNull(message = "Please enter the number of semesters")
    @Column(name = "num_of_semester")
    private Integer numberOfSemester;

    @Lob
    @NotBlank(message = "Program description can't be blank")
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
