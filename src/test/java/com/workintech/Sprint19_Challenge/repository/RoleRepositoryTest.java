package com.workintech.Sprint19_Challenge.repository;

import com.workintech.Sprint19_Challenge.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    private RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @DisplayName("Find role by authority")
    @Test
    void findByAuthority() {
        Role role = roleRepository.findByAuthority("USER").get();

        assertEquals("USER", role.getAuthority());
    }
}