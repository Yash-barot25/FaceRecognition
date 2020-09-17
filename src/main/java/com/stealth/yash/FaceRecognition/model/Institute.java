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
@Table(name = "institute")
public class Institute {

    @Id
    @NotNull
    @Column(name = "institute_code")
    private Long instituteCode;

    @Column(name = "institute_name")
    private String name;

    @Column(name = "institute_address")
    private String address;

    @Column(name = "institute_email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dean_id")
    private Dean dean;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "institute")
    Set<Department> departments;

}
