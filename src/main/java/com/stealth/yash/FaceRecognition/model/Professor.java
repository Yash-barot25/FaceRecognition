package com.stealth.yash.FaceRecognition.model;

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
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long id;

    @Lob
    @Column(name = "image")
    private String imageURL;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "professor_experience")
    private Double experience;

    @Column(name = "professor_projects")
    private Long projects;

    @Column(name = "professor_email")
    private String email;

    @Column(name = "professor_contact_number")
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Campus campus;

    @Enumerated(value = EnumType.STRING)
    private Level level;

    @Enumerated(value = EnumType.STRING)
    private Availibility availibility;

    @Lob
    @Column(name = "professor_bio")
    private String bio;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
//    private Set<Experty> experties;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "professor")
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return getId().equals(professor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
