package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.repository.TweetRepository;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    private TweetService tweetService;
    private UserService userService;

    @Mock
    private TweetRepository tweetRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        tweetService = new TweetServiceImpl(tweetRepository, userRepository);
    }

    @Test
    void findAll() {
        User user = new User();
        user.setFullName("User Test");

        Tweet tweet = new Tweet();
        tweet.setContent("Test Tweet");
        tweet.setUser(user);

        when(tweetRepository.findAll()).thenReturn(List.of(tweet));
        tweetService.findAll();

        verify(tweetRepository).findAll();
    }

    @Test
    void findById() {
        User user = new User();
        user.setFullName("User Test");

        Tweet tweet = new Tweet();
        tweet.setContent("Test Tweet");
        tweet.setUser(user);

        when(tweetRepository.findById(1L))
                .thenReturn(Optional.of(tweet));
        tweetService.findById(1L);

        verify(tweetRepository).findById(1L);
    }

    @Test
    void findByUserId() {
        User user = new User();
        user.setId(1L);
        user.setFullName("User Test");

        Tweet tweet = new Tweet();
        tweet.setContent("Test Tweet");
        tweet.setUser(user);

        TweetResponse tweetResponse = new TweetResponse(tweet.getContent(), tweet.getUser().getFullName());
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(tweetRepository.findByUserId(1L))
                .thenReturn(List.of(tweetResponse));

        tweetService.findByUserId(1L);
        verify(tweetRepository).findByUserId(1L);
    }


}