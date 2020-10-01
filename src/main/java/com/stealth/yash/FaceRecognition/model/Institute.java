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
@Table(name = "institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_code")
    private Long id;

    @Column(name = "institute_name")
    private String name;

    @Lob
    @Column(name = "institute_address")
    private String address;

    @Column(name = "institute_email")
    private String email;

    @Lob
    @Column(name = "institute_description")
    private String description;

    @Column(name = "institute_contact_number")
    private String contactNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
    Set<Department> departments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institute)) return false;
        Institute institute = (Institute) o;
        return getId().equals(institute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
