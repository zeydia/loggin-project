package com.elijah.loggin_project.repositories;

import com.elijah.loggin_project.dtos.UserDTO;
import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.userId = :id")
    Optional<User> findByIdWithRoles(@Param("id") Long id);

    boolean existsByUsername(String username);

    List<User> findAllByRole(Role role);
}
