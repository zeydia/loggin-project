package com.elijah.loggin_project.services;

import com.elijah.loggin_project.config.security.sevices.UserDetailsImpl;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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
        User createdUser = new User();
        createdUser.setUsername(user.getUsername());
        createdUser.setPassword("{noop}"+user.getPassword());
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

    public void updateUser(User user, UserDetailsImpl currentUser) {
        String username = user.getUsername();
        String email = user.getEmail();
        String fullname = user.getFullname();
        String mobile = user.getMobile();
        String password = user.getPassword();

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
        else if (!requestedUser.getPassword().equals(password)) {
            requestedUser.setPassword("{noop}"+password);
        }
        else if (!requestedUser.getMobile().equals(mobile)) {
            requestedUser.setMobile(mobile);
        }
    }


    public String getUserRole(Long id) {
        return userRepository.findById(id).get().getRole().toString();
    }
}
