package com.elijah.loggin_project.services;

import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByName(ROLE roleName){
        return roleRepository.findByRoleName(roleName).orElse(null);
    }

    public Role createRole(Role role){
        return roleRepository.save(role);
    }

    public void deleteRole(Role role){
        roleRepository.delete(role);
    }
}
