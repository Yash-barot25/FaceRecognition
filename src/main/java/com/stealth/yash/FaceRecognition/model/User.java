/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Model - User
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
@Table(name = "Users")
public class User implements UserDetails {

    //Specifies the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //username is non null

    @NonNull
    private String useremail;

    //encryptedPassword is non null
    @NonNull
    private String Password;
    @NonNull
    private boolean enabled;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "role_id")
    private Role role;


    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        privileges.add(role.getName().name());
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege));
        }
        return authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges(role));
    }

    @Override
    public String getPassword() {
        return this.Password;
    }

    @Override
    public String getUsername() {
        return this.useremail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
