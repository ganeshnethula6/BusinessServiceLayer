package com.example.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.Comment;
import com.example.application.model.PromotionDetail;
import com.example.application.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public Comment findCommentId(int promotionId) {
		return commentRepository.findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + promotionId));
	}

	public Comment saveComment(Comment existingComment) {
	
		return commentRepository.save(existingComment);
	}

	public List<Comment> getAllComments() {
		
		return commentRepository.findAll();
	}
	

}
