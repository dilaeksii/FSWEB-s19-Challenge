package com.workintech.Sprint19_Challenge.repository;

import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TweetRepositoryTest {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetRepositoryTest(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    private User user;
    private Tweet tweet_one;
    private Tweet tweet_two;

    @BeforeEach
    void setUp() {
        user = userRepository.findById(1L).get();

        userRepository.save(user);

        tweet_one = new Tweet();
        tweet_one.setContent("Test 12345 Test");
        tweet_one.setUser(user);

        tweet_two = new Tweet();
        tweet_two.setContent("Test test test");
        tweet_two.setUser(user);

        tweetRepository.save(tweet_one);
        tweetRepository.save(tweet_two);
    }

    @AfterEach
    void tearDown() {
        tweetRepository.delete(tweet_one);
        tweetRepository.delete(tweet_two);
    }

    @Test
    void findByUserId() {
        List<TweetResponse> foundByUserId = tweetRepository.findByUserId(user.getId());

        List<String> content = foundByUserId.stream()
                        .map(TweetResponse::content)
                                .toList();

        assertFalse(foundByUserId.isEmpty());
        assertTrue(content.contains("Test 12345 Test"));

    }
}