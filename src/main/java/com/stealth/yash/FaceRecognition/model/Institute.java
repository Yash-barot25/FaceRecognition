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
@Table(name = "institute")
public class Institute {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "institute_code")
    private Long id;

    @Column(name = "institute_name")
    private String name;

    @Column(name = "institute_address")
    private String address;

    @Column(name = "institute_email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "institute")
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
