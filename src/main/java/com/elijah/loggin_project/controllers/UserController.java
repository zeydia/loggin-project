package com.elijah.loggin_project.controllers;

import com.elijah.loggin_project.dtos.UserDTO;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.repositories.UserRepository;
import com.elijah.loggin_project.services.RoleService;
import org.modelmapper.ModelMapper;
import com.elijah.loggin_project.config.security.sevices.UserDetailsImpl;
import com.elijah.loggin_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
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
            @RequestParam String fullname,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String mobile,
            @RequestParam String role
    ) {

        User requestedUser = userService.getUserByUsername(username);
        if (requestedUser != null) {
            return ResponseEntity.badRequest().body(null);
        }
        else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setMobile(mobile);

            Role userRole = roleService.getRoleByName(role.contains("ADMIN") ? ROLE.ROLE_ADMIN : ROLE.ROLE_USER);
            user.setRole(userRole);

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

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam String fullname,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String mobile
    ){
        User user = userService.getUserByUsername(userDetails.getUsername());

        if (!user.getFullname().equals(fullname)) {
            user.setFullname(fullname);
        }
        else if (!user.getUsername().equals(username)) {
            user.setUsername(username);
        }
        else if (!user.getEmail().equals(email)) {
            user.setEmail(email);
        }
        else if (!user.getPassword().equals(password)) {
            user.setPassword(password);
        }
        else if (!user.getMobile().equals(mobile)) {
            user.setMobile(mobile);
        }

    }

}
