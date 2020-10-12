/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - Role
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//Generates an all-args constructor
@AllArgsConstructor
//Generates a no-args constructor
@NoArgsConstructor
// Generates getters for all fields
// a useful toString method,
// and hashCode and equals implementations that check all non-transient field
@Data
//Specifies that this class is an entity
@Entity
@Builder

public class Role {

	//Specifies the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//Specifies rolename is not null
	@NonNull
	private String rolename;

	/**
	 * Getter method for rolename
	 * @return role name
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * Setter method for rolename
	 * @return role name
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@ManyToMany(cascade=CascadeType.ALL, mappedBy="roles")
	private List<User> users = new ArrayList<User>();
}
