package com.workintech.Sprint19_Challenge.repository;

import com.workintech.Sprint19_Challenge.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
