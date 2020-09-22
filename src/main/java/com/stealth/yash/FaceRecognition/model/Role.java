package com.stealth.yash.FaceRecognition.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", length = 20)
	Roles name;


}
