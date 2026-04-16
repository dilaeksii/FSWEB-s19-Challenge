package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.entity.Role;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void findById() {
        Role role = new Role(1L, "USER");

        User user = new User();
        user.setId(1L);
        user.setFullName("User 2");
        user.setEmail("user2@gmail.com.tr");
        user.setPassword("123456");
        user.setAuthorities(Set.of(role));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        userService.findById(1L);
        verify(userRepository).findById(1L);
    }
}