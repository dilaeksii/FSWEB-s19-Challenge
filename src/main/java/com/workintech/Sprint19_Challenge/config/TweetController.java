package com.workintech.Sprint19_Challenge.config;


import com.workintech.Sprint19_Challenge.dto.TweetRequest;
import com.workintech.Sprint19_Challenge.dto.TweetResponse;
import com.workintech.Sprint19_Challenge.entity.Tweet;
import com.workintech.Sprint19_Challenge.service.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetServiceImpl tweetService;

    @Autowired
    public TweetController(TweetServiceImpl tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<TweetResponse> findAll() {
        return tweetService.findAll();
    }

    @GetMapping("/{id}")
    public TweetResponse findById(@PathVariable Long id) {
        return tweetService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<TweetResponse> findByUserId(@PathVariable Long id) {
        return tweetService.findByUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public TweetResponse delete(@PathVariable Long id) {
        return tweetService.delete(id);
    }

    @PostMapping("/post")
    public TweetResponse save(@RequestBody TweetRequest tweetRequest) {
        return tweetService.save(tweetRequest);
    }

    @PutMapping("/put/{id}")
    public TweetResponse update(@RequestBody TweetRequest tweetRequest, @PathVariable Long id) {
        return tweetService.update(tweetRequest, id);
    }
}
