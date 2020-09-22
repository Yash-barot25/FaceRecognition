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
@Table(name = "student")
public class Student{


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long id;

    @Lob
    @Column(name = "image")
    private Byte[] image;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "program_id")
    private Program program;


    public void setProgram(Program program) {
        this.program = program;
        this.program.addStudent(this);
    }

    /*public void removeProgram(){
        this.program.removeStudent(this);
        this.program = null;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
