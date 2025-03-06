package com.elijah.loggin_project.controllers;

import com.elijah.loggin_project.config.security.sevices.UserDetailsImpl;
import com.elijah.loggin_project.dtos.UserDTO;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.services.RoleService;
import com.elijah.loggin_project.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAllUsersByRole(
            @RequestParam(value = "role", required = false) String role
    ){

        List<User> users = null;

        if(role != null){
            ROLE roleEnum = role.toUpperCase().contains("ADMIN") ? ROLE.ROLE_ADMIN : ROLE.ROLE_USER;
            Role roleObj = roleService.getRoleByName(roleEnum);
            users = userService.getUsersByRole(roleObj);
        }
        else {
            users = userService.getAllUsers();
        }

        List<UserDTO> usersResponse = new ArrayList<>();
        for (User user: users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFullname(user.getFullname());
            userDTO.setMobile(user.getMobile());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole().getRoleName());

            usersResponse.add(userDTO);
        }

        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setMobile(user.getMobile());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().getRoleName());

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("user")
    public void deleteUserById(
            @RequestParam(value = "id", required = false) Long id
    ){
        if(id != null){
            User user = userService.getUserById(id);
            userService.deleteUser(user);
        }
    }

    @GetMapping("roles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }



}
