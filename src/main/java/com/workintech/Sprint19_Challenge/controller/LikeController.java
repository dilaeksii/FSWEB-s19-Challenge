package com.workintech.Sprint19_Challenge.controller;


import com.workintech.Sprint19_Challenge.dto.LikeResponse;
import com.workintech.Sprint19_Challenge.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{id}")
    public LikeResponse like(@PathVariable Long id) {
        return likeService.like(id);
    }

    @DeleteMapping("/{id}")
    public LikeResponse dislike(@PathVariable Long id) {
        return likeService.dislike(id);
    }
}
