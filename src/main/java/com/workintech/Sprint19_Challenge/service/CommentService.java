package com.workintech.Sprint19_Challenge.service;

import com.workintech.Sprint19_Challenge.dto.CommentRequest;
import com.workintech.Sprint19_Challenge.dto.CommentResponse;
import com.workintech.Sprint19_Challenge.entity.Comments;

public interface CommentService {
    CommentResponse save(CommentRequest commentRequest, Long id);
    CommentResponse update(CommentRequest commentRequest, Long id);
    CommentResponse delete(Long id);
}
