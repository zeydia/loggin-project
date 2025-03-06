package com.elijah.loggin_project.controllers;


import com.elijah.loggin_project.config.security.auth.AuthResponse;
import com.elijah.loggin_project.config.security.sevices.UserDetailsSeviceImpl;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody User user
    ) {
        System.out.println("Request body: "+user);
        System.out.println("Login processing...");

        String token = userService.authenticateUser(user);
        System.out.println("token: "+token);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("Login Successful.");


        System.out.println("Authentication Success, session saved...");
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody User user
    ) {
        System.out.println(user);
        System.out.println("Signup...");

        User createdUser = userService.createUser(user);

        String token = userService.authenticateUser(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("Signup Successful.");

        System.out.println("User registered successfully: "+createdUser);

        return ResponseEntity.ok(authResponse);
    }


}
