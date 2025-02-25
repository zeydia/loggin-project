package com.elijah.loggin_project.services;

import com.elijah.loggin_project.config.security.sevices.JWTService;
import com.elijah.loggin_project.config.security.sevices.UserDetailsImpl;
import com.elijah.loggin_project.config.security.sevices.UserDetailsSeviceImpl;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsSeviceImpl userDetailsSeviceImpl;
    @Autowired
    JWTService jwtService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getUserWithRole(Long id) {
        return userRepository.findByIdWithRoles(id).orElse(null);
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            System.out.println("Username already exists");
            throw new RuntimeException("User already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("Email already exists");
            throw new RuntimeException("Email already exists");
        }

        User createdUser = new User();
        createdUser.setUsername(user.getUsername());
        createdUser.setPassword(encoder.encode(user.getPassword()));
        createdUser.setFullname(user.getFullname());
        createdUser.setEmail(user.getEmail());
        createdUser.setMobile(user.getMobile());

        Role userRole = roleRepository.findByRoleName(user.getRole().toString().contains("ADMIN") ? ROLE.ROLE_ADMIN : ROLE.ROLE_USER)
                .orElse(null);
        createdUser.setRole(userRole);
        return userRepository.save(createdUser);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User Updateduser, UserDetailsImpl currentUser) {
        String username = Updateduser.getUsername();
        String email = Updateduser.getEmail();
        String fullname = Updateduser.getFullname();
        String mobile = Updateduser.getMobile();

        User requestedUser = this.getUserByUsername(currentUser.getUsername());

        if (!requestedUser.getFullname().equals(fullname)) {
            requestedUser.setFullname(fullname);
        }
        else if (!requestedUser.getUsername().equals(username)) {
            requestedUser.setUsername(username);
        }
        else if (!requestedUser.getEmail().equals(email)) {
            requestedUser.setEmail(email);
        }
        else if (!requestedUser.getMobile().equals(mobile)) {
            requestedUser.setMobile(mobile);
        }
    }

    public void updateUserPassword(User Updateduser, UserDetailsImpl currentUser) {
        String username = Updateduser.getUsername();
        String password = Updateduser.getPassword();

        User requestedUser = this.getUserByUsername(currentUser.getUsername());
        requestedUser.setPassword(encoder.encode(password));

    }

    public String verify(User user){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        else {
            System.out.println("Authentication Failed");
            return "Wrong credentials";
        }
    }


    public String getUserRole(Long id) {
        return userRepository.findById(id).get().getRole().toString();
    }
}
