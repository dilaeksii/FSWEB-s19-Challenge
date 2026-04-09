package com.workintech.Sprint19_Challenge.controller;


import com.workintech.Sprint19_Challenge.dto.CommentRequest;
import com.workintech.Sprint19_Challenge.dto.CommentResponse;
import com.workintech.Sprint19_Challenge.entity.Comments;
import com.workintech.Sprint19_Challenge.repository.CommentRepository;
import com.workintech.Sprint19_Challenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}")
    public CommentResponse save(@RequestBody CommentRequest commentRequest, @PathVariable Long id) {
        return commentService.save(commentRequest, id);
    }

    @PutMapping("/{id}")
    public CommentResponse update(@RequestBody CommentRequest commentRequest, @PathVariable Long id) {
        return commentService.update(commentRequest, id);
    }

    @DeleteMapping("/{id}")
    public CommentResponse delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
}
