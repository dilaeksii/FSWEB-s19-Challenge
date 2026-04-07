package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.exceptions.ApplicationException;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User is not valid!");
                });
    }

    public User findById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(() -> new ApplicationException("User with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        return user;
    }
}
