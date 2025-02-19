package com.elijah.loggin_project.dtos;


import com.elijah.loggin_project.enumeration.ROLE;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private ROLE role;
}
