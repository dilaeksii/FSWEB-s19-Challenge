package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.LikeResponse;

public interface LikeService {
        LikeResponse like(Long id);
        LikeResponse dislike(Long id);
}
