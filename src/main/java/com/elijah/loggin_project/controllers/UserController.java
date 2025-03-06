package com.elijah.loggin_project.controllers;

import com.elijah.loggin_project.dtos.UserDTO;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.repositories.UserRepository;
import com.elijah.loggin_project.services.RoleService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import com.elijah.loggin_project.config.security.sevices.UserDetailsImpl;
import com.elijah.loggin_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        System.out.println("User principal: "+ SecurityContextHolder.getContext().getAuthentication());

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);

        ROLE userRole = null;
        for(String role : roles){
            userRole = role.contains("ADMIN") ? ROLE.ROLE_ADMIN : ROLE.ROLE_USER;
        }
        userDTO.setRole(userRole);

        System.out.println(userDTO);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(
            @RequestParam User user
    ) {

        User requestedUser = userService.getUserByUsername(user.getUsername());
        if (requestedUser != null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(userService.createUser(user));
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        userService.deleteUser(user);
    }

    @PostMapping("updateUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> updateUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam User user
    ){
        System.out.println("Updating user");
        User requestedUser = userService.getUserByUsername(user.getUsername());
        if (requestedUser != null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            return ResponseEntity.ok(userService.updateUser(user, userDetails));
        }
    }

    @PostMapping("renewPass")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserPassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam String newPassword
    ){
        userService.updateUserPassword(newPassword, userDetails);
    }
}
