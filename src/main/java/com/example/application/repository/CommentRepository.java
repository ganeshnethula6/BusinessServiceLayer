package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
