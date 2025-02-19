package com.elijah.loggin_project.services;

import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public String getUserRole(Long id) {
        return userRepository.findById(id).get().getRole().toString();
    }
}
