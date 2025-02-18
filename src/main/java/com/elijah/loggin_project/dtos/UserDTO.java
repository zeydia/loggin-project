package com.elijah.loggin_project.dtos;


import com.elijah.loggin_project.enumeration.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private ROLE role;
}
