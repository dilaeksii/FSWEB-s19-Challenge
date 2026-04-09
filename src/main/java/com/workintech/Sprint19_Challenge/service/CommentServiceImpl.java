package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.CommentRequest;
import com.workintech.Sprint19_Challenge.dto.CommentResponse;
import com.workintech.Sprint19_Challenge.entity.Comments;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.entity.User;
import com.workintech.Sprint19_Challenge.exceptions.ApplicationException;
import com.workintech.Sprint19_Challenge.repository.CommentRepository;
import com.workintech.Sprint19_Challenge.repository.TweetRepository;
import com.workintech.Sprint19_Challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }


    @Override
    public CommentResponse save(CommentRequest commentRequest, Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tweet with id number " + id + " does not exist", HttpStatus.NOT_FOUND));

        Comments comment = new Comments();
        comment.setComment(commentRequest.comment());
        comment.setUser(user);
        comment.setTweet(tweet);
        commentRepository.save(comment);
        return new CommentResponse(comment.getComment(), comment.getTweet().getContent(), comment.getUser().getFullName());
    }

    @Override
    public CommentResponse update(CommentRequest commentRequest, Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Comment with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        if (comment.getUser().getId().equals(user.getId())) {
            comment.setComment(commentRequest.comment());
            commentRepository.save(comment);
        } else {
            throw new ApplicationException("You cannot edit someone's comment.", HttpStatus.FORBIDDEN);
        }
        return new CommentResponse(comment.getComment(), comment.getTweet().getContent(), comment.getUser().getFullName());
    }

    @Override
    public CommentResponse delete(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            user = userRepository.findUserByEmail(username).get();
        }
        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Comment with id number " + id + " does not exist", HttpStatus.NOT_FOUND));
        if (comment.getUser().getId().equals(user.getId()) || comment.getTweet().getUser().getId().equals(user.getId())) {
            commentRepository.delete(comment);
        } else {
            throw new ApplicationException("You cannot delete someone's comment.", HttpStatus.FORBIDDEN);
        }
        return new CommentResponse(comment.getComment(), comment.getTweet().getContent(), comment.getUser().getFullName());
    }
}
