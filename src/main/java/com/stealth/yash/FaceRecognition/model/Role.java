/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Role
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import com.stealth.yash.FaceRecognition.enums.Roles;
import lombok.*;

import javax.persistence.*;

//Generates an all-args constructor
@AllArgsConstructor
//Generates a no-args constructor
@NoArgsConstructor
// Generates getters for all fields
// a useful toString method,
// and hashCode and equals implementations that check all non-transient field
@Data
//Specifies that this class is an entity
@Getter
@Setter
@Entity(name = "Roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", length = 20)
    Roles name;


	public Role(Roles name) {
		this.name = name;
	}


}
