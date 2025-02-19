package com.elijah.loggin_project.init;

import com.elijah.loggin_project.entities.Role;
import com.elijah.loggin_project.entities.User;
import com.elijah.loggin_project.enumeration.ROLE;
import com.elijah.loggin_project.repositories.RoleRepository;
import com.elijah.loggin_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class init implements CommandLineRunner {
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Role userRole = roleRepository.findByRoleName(ROLE.ROLE_USER)
//                .orElseGet(()->new Role(ROLE.ROLE_USER));
//
//        Role adminRole = roleRepository.findByRoleName(ROLE.ROLE_ADMIN)
//                .orElseGet(()->new Role(ROLE.ROLE_ADMIN));
//
//        if(!userRepository.existsByUsername("user1")){
//            User user = new User(
//                    "user1",
//                    "passer"
//            );
//            user.setRole(userRole);
//            user.setEmail("user1@email.com");
//            user.setFullname("user user");
//            user.setMobile("77 777 77 77");
//            System.out.println(user);
//            userRepository.save(user);
//        }
//
//        if(!userRepository.existsByUsername("admin1")){
//            User user = new User(
//                    "admin1",
//                    "passer"
//            );
//            user.setRole(adminRole);
//            user.setEmail("admin1@email.com");
//            user.setFullname("admin admin");
//            user.setMobile("77 777 77 77");
//            userRepository.save(user);
//        }
//    }
//}
