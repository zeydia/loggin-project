package com.elijah.loggin_project.config.security.sevices;

import com.elijah.loggin_project.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

    private Long userDetailsId;
    private String fullname;
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    private String mobile;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getFullname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getMobile(),
                List.of(authority)
        );
    }
}
