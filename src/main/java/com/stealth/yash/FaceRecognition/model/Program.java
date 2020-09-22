package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , mappedBy = "programs")
    private Set<Professor> professors;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "program")
    private Set<Student> students;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , mappedBy = "programs")
    private Set<Course> courses;

    public void addStudent(Student student){
        removeStudent(student);
        this.students.add(student);
    }

    public void setDepartment(Department department) {
        this.department = department;
//        department.getPrograms().add(this);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

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
