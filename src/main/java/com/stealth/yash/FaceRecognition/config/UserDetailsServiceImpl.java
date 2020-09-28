package com.stealth.yash.FaceRecognition.config;


import com.stealth.yash.FaceRecognition.model.Role;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;


	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.stealth.yash.FaceRecognition.model.User user = userRepo.findByUseremail(useremail);
		
		if (user == null) {
			System.out.println("User not found:" + useremail);
			throw new UsernameNotFoundException("User " + useremail + " was not found in the database");
		}
		
		List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
		for (Role role: user.getRoles()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		UserDetails userDetails= (UserDetails) new User(user.getUsername(), user.getEncryptedPassword(), grantList);
		return userDetails;
		
	}
	
	

}
