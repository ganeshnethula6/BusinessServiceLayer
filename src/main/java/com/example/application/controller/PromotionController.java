package com.example.application.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.exception.NullCheckException;
import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.Comment;
import com.example.application.model.PromotionDetail;
import com.example.application.service.ClientPromotionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/online-advertisement-application")
public class PromotionController {
	@Autowired
	public ClientPromotionService promotionService;
	@PostMapping("/promotions")
	public PromotionDetail newPromotion(@RequestBody PromotionDetail user)
	{
		return this.promotionService.savePromotion(user);	
	}
	@GetMapping("/promotions")
	public List<PromotionDetail> getPromotionDetails(){
		return promotionService.getPromotionRepository().findAll();
	}	
	
	@GetMapping("/promotions/category/{addType}")
		public ResponseEntity<List<PromotionDetail>> getPromotionDetailsByCategory(@PathVariable(value = "addType") String addType)
			throws ResourceNotFoundException {
		List<PromotionDetail> promotionDetail = promotionService.findUserByAddType(addType);
		return ResponseEntity.ok().body(promotionDetail);
	}
	
	@DeleteMapping("/promotions/{id}")
	public Map<String, Boolean> deletePromotion(@PathVariable(value = "id") int promotionId)
			throws ResourceNotFoundException {
		PromotionDetail promotion = promotionService.getPromotionRepository().findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found for this ID :: '" + promotionId+"'"));

		promotionService.getPromotionRepository().delete(promotion);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/promotions/{id}")
	public ResponseEntity<PromotionDetail> updatePromotion(@PathVariable(value = "id") int promotionId,
			@RequestBody PromotionDetail promotion) throws ResourceNotFoundException {
		PromotionDetail existingPromotion = promotionService.getPromotionRepository().findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + promotionId));
		existingPromotion.setAddType(promotion.getAddType());
		existingPromotion.setAddTitle(promotion.getAddTitle());
		existingPromotion.setAddTag(promotion.getAddTag());
		existingPromotion.setAddDesc(promotion.getAddDesc());
	//	existingUser.setAddFile(user.getAddFile());
	//	existingUser.setUpdatedDate(new Date());

		final PromotionDetail updatedPromotion =  promotionService.getPromotionRepository().save(existingPromotion);
		return ResponseEntity.ok(updatedPromotion);
		
	}
	@PutMapping("/promotion/comment/{id}")
	public ResponseEntity<PromotionDetail> addCommentById(@PathVariable(value="id") int promotionId,@RequestBody PromotionDetail promotionDetail)
	{
		if(promotionDetail==null)
	{
		throw new NullCheckException("Promotion Details  should not be null");
	}
		PromotionDetail existingPromotion=promotionService.getPromotionRepository().findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + promotionId));
		Comment comment=promotionDetail.getComment().get(0);
		comment.setCreatedDate(new Date());
		System.out.println(comment.getCreatedDate());
		if(comment.getComment()==null&& comment.getCreatedDate()==null)
		{
			throw new NullCheckException("Comment should not be null");
		}
	
	existingPromotion.addComment(comment);
		
		
		PromotionDetail updatedPromotionDetail=promotionService.getPromotionRepository().save(existingPromotion);
		return ResponseEntity.ok(updatedPromotionDetail);
	}
	//Getting the comments by using promotion Id:
	@GetMapping("/promotion/comment/{id}")
	public ResponseEntity<List<Comment>> getCommentsById(@PathVariable(value="id") int promotionId )throws ResourceNotFoundException 
	{
		PromotionDetail promotionDetail=promotionService.getPromotionRepository().findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + promotionId));
		
		return ResponseEntity.ok().body(promotionDetail.getComment());
		
	}
	
}
