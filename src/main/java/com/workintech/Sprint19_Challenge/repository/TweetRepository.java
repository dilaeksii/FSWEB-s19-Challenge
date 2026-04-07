package com.workintech.Sprint19_Challenge.repository;

import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t.content, t.user.fullName FROM Tweet t WHERE t.user.id = :id")
    List<TweetResponse> findByUserId(Long id);
}
