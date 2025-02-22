package com.elijah.loggin_project.controllers;


import com.elijah.loggin_project.config.security.sevices.UserDetailsSeviceImpl;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserDetailsSeviceImpl userDetailsServiceImpl;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public void login(
            @RequestBody User user
    ) {
        System.out.println("Request body: "+user);
        System.out.println("Login processing...");

        if (userDetailsServiceImpl.verifyCredentials(user)) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }

        System.out.println("Authentication Success, session saved...");
        return;
    }

    @PostMapping("signup")
    public void signup(
            @RequestBody User user
    ) {
        System.out.println(user);
        System.out.println("Signup...");

        User createdUser = userService.createUser(user);
        System.out.println("User registered successfully: "+createdUser);

        return ;
    }

    @GetMapping("logout")
    public String logout() {
        System.out.println("logout...");
        return "logout...";
    }

}
