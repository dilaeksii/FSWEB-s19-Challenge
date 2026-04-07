package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.TweetRequest;
import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetResponse> findAll();
    TweetResponse findById(Long id);
    List<TweetResponse> findByUserId(Long id);
    TweetResponse delete(Long id);
    TweetResponse save(TweetRequest tweetRequest);
    TweetResponse update(TweetRequest tweetRequest, Long id);
}
