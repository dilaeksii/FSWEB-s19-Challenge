package com.workintech.Sprint19_Challenge.service;


import com.workintech.Sprint19_Challenge.dto.TweetRequest;
import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.exceptions.ApplicationException;
import com.workintech.Sprint19_Challenge.repository.TweetRepository;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TweetResponse> findAll() {
        return tweetRepository
                .findAll()
                .stream()
                .map((tweet) -> new TweetResponse(
                        tweet.getContent(),
                        tweet.getUser().getFullName()
                )).toList();
    }

    @Override
    public TweetResponse findById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        return new TweetResponse(tweet.getContent(), tweet.getUser().getFullName());
    }

    @Override
    public List<TweetResponse> findByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("User with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        return tweetRepository.findByUserId(user.getId());
    }

    @Override
    public TweetResponse delete(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        if (tweet.getUser().getId().equals(user.getId())) {
            tweetRepository.delete(tweet);
            return new TweetResponse(tweet.getContent(), tweet.getUser().getFullName());
        } else {
            return null;
        }
    }

    @Override
    public TweetResponse save(TweetRequest tweetRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.content());
        tweet.setUser(user);
        tweetRepository.save(tweet);
        TweetResponse tweetResponse = new TweetResponse(tweet.getContent(), tweet.getUser().getFullName());
        return tweetResponse;
    }

    @Override
    public TweetResponse update(TweetRequest tweetRequest, Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        if (tweet.getUser().getId().equals(user.getId())) {
            tweet.setContent(tweetRequest.content());
            tweetRepository.save(tweet);
        }
        return new TweetResponse(tweet.getContent(), tweet.getUser().getFullName());
    }


}
