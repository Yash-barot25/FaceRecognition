/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - User
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

//Generates an all-args constructor
@AllArgsConstructor
//Generates a no-args constructor
@NoArgsConstructor
//Generates getters for all fields,  a useful toString method,
// and hashCode and equals implementations  that check all non-transient fields
@Data
//Specifies this class is an Entity
@Entity
//Put on any field to make lombok build a standard getter.
@Getter
//Put on any field to make lombok build a standard setter.
@Setter
@Builder
//Generates a constructor with required arguments
@RequiredArgsConstructor

public class User {

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //username is non null
    @NonNull
    private String username;
    //useremail is non null
    @NonNull
    private String useremail;

    /**
     *getter method for userEmail
     * @return useremail
     */
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    //encryptedPassword is non null
    @NonNull
    private String encryptedPassword;
    @NonNull
    private byte enabled;


    @ManyToMany(cascade=CascadeType.ALL)
   // Define the fetching strategy used for roles
    @Fetch(value = FetchMode.SUBSELECT)
    //Define the fetching strategy used for roles
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roles = new ArrayList<Role>();

    /**
     *getter method for userName
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *setter method for userName
     *  @param username an object of type String
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *getter method for EncryptedPassword
     * @return encryptedPassword
     */
    public String getEncryptedPassword() {
        return encryptedPassword;
    }


    /**
     *setter method for EncryptedPassword
     *  @param encryptedPassword an object of type swtring
     */
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    /**
     *getter method for Enabled
     * @return enabled
     */
    public byte getEnabled() {
        return enabled;
    }

    /**
     *setter method for enabled
     *  @param enabled an object of type byte
     */

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    /**
     *getter method for Roles
     * @return roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     *setter method for Roles
     *  @param roles an object of type List
     */

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



}
