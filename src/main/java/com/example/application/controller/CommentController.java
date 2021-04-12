package com.example.application.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.Comment;
import com.example.application.model.PromotionDetail;
import com.example.application.model.Replay;
import com.example.application.service.CommentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/online-advertisement-application")
public class CommentController {

	@Autowired
	public CommentService commentService;
	
	
	@PutMapping("/comment/{id}")
	ResponseEntity<Comment> addComment(@PathVariable(value="id") int commentId, @RequestBody Comment commentDetail)
	{
		Comment existingComment=commentService.findCommentId(commentId);
		existingComment.setComment(commentDetail.getComment());
		existingComment.setCommentBy(commentDetail.getCommentBy());
		Comment updatedComment=commentService.saveComment(existingComment);
		return ResponseEntity.ok(updatedComment);
		
	}
	@GetMapping("/promotion/comments")
	public ResponseEntity<List<Comment>> getCommentsById()
	{			
		return ResponseEntity.ok().body(commentService.getAllComments());
		
	}
//	
//	//addding replay to comments
//	
//	@PutMapping("/promotion/replay/{id}")
//	public ResponseEntity<Comment> addReplayById(@PathVariable(value="id") int commentId,@RequestBody Comment commentDetail)
//	{
//		Comment existingComment=commentService.findCommentId(commentId);		
//		Replay replay=commentDetail.getReplay().get(0);
//		replay.setReplayDate(new Date());
//	existingComment.addReplay(replay);
//		
//		
//	Comment updatedReplaytDetail=commentService.saveComment(existingComment);
//		return ResponseEntity.ok(updatedReplaytDetail);
//	}
//	//Getting the replay by using promotion Id:
//	@GetMapping("/promotion/replay/{id}")
//	public ResponseEntity<List<Replay>> getRepliesById(@PathVariable(value="id") int commentId )throws ResourceNotFoundException 
//	{
//		Comment commentDetail=commentService.findCommentId(commentId);	
//		
//		return ResponseEntity.ok().body(commentDetail.getReplay());
//		
//	}
	
}
