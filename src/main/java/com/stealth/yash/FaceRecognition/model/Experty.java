package com.stealth.yash.FaceRecognition.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experties")
public class Experty {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "experty")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
