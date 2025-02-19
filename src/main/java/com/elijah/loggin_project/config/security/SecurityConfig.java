package com.elijah.loggin_project.config.security;

import com.elijah.loggin_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests -> {
                    requests
                            .anyRequest().permitAll();
                }
        );
        http.csrf(AbstractHttpConfigurer::disable);
        //http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.cors(AbstractHttpConfigurer::disable);
        http.formLogin(Customizer.withDefaults());
        //http.formLogin(login-> login
        //        .loginPage("/auth/login")
        //        .permitAll()
        //);

        return http.build();
    }


}
