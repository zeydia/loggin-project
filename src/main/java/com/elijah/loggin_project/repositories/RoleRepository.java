package com.elijah.loggin_project.repositories;

import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.enumeration.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ROLE roleName);

}
