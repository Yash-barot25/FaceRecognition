package com.stealth.yash.FaceRecognition.config;


import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.stealth.yash.FaceRecognition.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        return userRepository.findUserByUseremail(useremail).orElseThrow(() -> new UserNotFoundException("User with " + useremail + " Not found"));

    }


}
