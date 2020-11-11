package com.stealth.yash.FaceRecognition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
//Put on any field to make lombok build a standard setter.
@Setter
//Generates a no-args constructor
@NoArgsConstructor
//Generates an all-args constructor
@AllArgsConstructor
//Specifies that this class is an entity
@Entity
//Specifies the primary table for student
@Table(name = "token")

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    private Student student;


}
