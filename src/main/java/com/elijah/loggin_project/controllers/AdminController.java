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

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsersByRole(
            @RequestParam(value = "role", required = false) String role
    ){
        if(role != null){
            ROLE roleEnum = role.toUpperCase().contains("ADMIN") ? ROLE.ROLE_ADMIN : ROLE.ROLE_USER;
            Role roleObj = roleService.getRoleByName(roleEnum);

            return ResponseEntity.ok(userService.getUsersByRole(roleObj));
        }

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        String role = user.getRole().getRoleName().toString();
        //System.out.println(user.getRole().getRoleName().toString());

        return ResponseEntity.ok(user);
    }

    @DeleteMapping
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
