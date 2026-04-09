package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.LikeResponse;
import com.workintech.Sprint19_Challenge.entity.Likes;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.exceptions.ApplicationException;
import com.workintech.Sprint19_Challenge.repository.LikeRepository;
import com.workintech.Sprint19_Challenge.repository.TweetRepository;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public LikeResponse like(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        Likes like = new Likes();
        like.setUser(user);
        like.setTweet(tweet);
        try {
            likeRepository.save(like);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException("You have already like this tweet.", HttpStatus.CONFLICT);
        }
        return new LikeResponse(like.getTweet().getContent(), like.getUser().getFullName());
    }

    @Override
    public LikeResponse dislike(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        Likes like = new Likes();
        like.setUser(user);
        like.setTweet(tweet);
        likeRepository.delete(like);
        return new LikeResponse(like.getTweet().getContent(), like.getUser().getFullName());
    }
}
