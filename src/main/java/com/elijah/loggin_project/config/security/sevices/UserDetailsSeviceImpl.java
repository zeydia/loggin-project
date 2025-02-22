package com.elijah.loggin_project.config.security.sevices;

import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSeviceImpl implements UserDetailsService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found"));

        return UserDetailsImpl.build(user);
    }


    @Transactional
    public UserDetailsImpl loadUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User "+email+" not found"));

        return UserDetailsImpl.build(user);
    }

    public Boolean verifyCredentials(User user){
        UserDetailsImpl userDetails = user.getUsername().contains("@") ? loadUserByEmail(user.getUsername()) : loadUserByUsername(user.getUsername());
        if (userDetails == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        if(! encoder.matches(user.getPassword(), userDetails.getPassword()) ) {
            System.out.println("password not match");
            throw new BadCredentialsException("Invalid password");
        }
        return true;
    }

}
