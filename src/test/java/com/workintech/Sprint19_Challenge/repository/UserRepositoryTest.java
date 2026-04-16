package com.workintech.Sprint19_Challenge.repository;

import com.workintech.Sprint19_Challenge.entity.Role;
import com.workintech.Sprint19_Challenge.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
       role = roleRepository.findByAuthority("USER").get();

       user = new User();
       user.setFullName("User 2");
       user.setEmail("user2@gmail.com.tr");
       user.setPassword("123456");
       user.setAuthorities(Set.of(role));

       userRepository.save(user);
    }

    @DisplayName("Find user by email.")
    @Test
    void findUserByEmail() {
        User foundUser = userRepository.findUserByEmail("user2@gmail.com.tr").get();
        assertEquals("user2@gmail.com.tr", foundUser.getEmail());
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }
}