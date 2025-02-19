package com.elijah.loggin_project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @NotNull
    @Column(name = "fullname", length = 50)
    private String fullname;


    @Column(name = "username", length = 50)
    private String username;

    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;


    @JsonIgnore
    @Column(name = "password", length = 50)
    private String password;


    @Column(name = "mobile", length = 50)
    private String mobile;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() != null && Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


}
