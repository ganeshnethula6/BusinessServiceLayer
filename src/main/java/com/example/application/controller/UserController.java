package com.example.application.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.exception.NullCheckException;
import com.example.application.exception.ResourceNotFoundException;

import com.example.application.model.PromotionDetail;
import com.example.application.model.User;
import com.example.application.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/online-advertisement-application")

public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public List<User> getUser() {
		return service.getUserRepository().findAll();
	}

	// Register new user
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		User userObj=service.getUserAfterSave(user);	
		if(userObj==null)
			throw new NullCheckException("Fill all the Details");
		
		return userObj;
	}

	// login user
	@PostMapping("/register/{id}")
	public User createUserById(@PathVariable(value = "id") int id, @RequestBody User user) throws Exception {
		User userObj = service.getUserForLoginById(id, user);
		return userObj;
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		User userObj=service.userDetailsForLogin(user);
		return userObj;
	}

	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
		User user = service.getUserRepository().findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found for this ID :: '" + userId + "'"));

		service.getUserRepository().delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User user)
			throws ResourceNotFoundException {
		User existingUser = service.getUserRepository().findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: '" + userId + "'"));
		existingUser.setRoleType(user.getRoleType());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setDob(user.getDob());
		existingUser.setMobileNumber(user.getMobileNumber());
		// existingUser.setUpdatedDate(new Date());

		final User updatedPromotion = service.getUserRepository().save(existingUser);
		return ResponseEntity.ok(updatedPromotion);

	}

//	@PutMapping("/user/promotion/{id}")
//	public ResponseEntity<User> createPromotionByUserId(@PathVariable(value = "id") int userId, @RequestBody User user)
//			throws ResourceNotFoundException {
//		User existingUser = service.getUserRepository().findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//		PromotionDetail promotion = user.getPromotionDetail().get(0);
//
//		promotion.setCreatedDate(new Date());
//		promotion.setUpdatedDate(new Date());
//		existingUser.addPromotion(promotion);
//
//		final User updatedPromotion = service.getUserRepository().save(existingUser);
//		return ResponseEntity.ok(updatedPromotion);
//
//	}

	@GetMapping("/promotions/{id}")
	public ResponseEntity<List<PromotionDetail>> getPromotionDetailsById(@PathVariable(value = "id") int promotionId)
			throws ResourceNotFoundException {
		User user = service.getUserRepository().findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + promotionId));
		return ResponseEntity.ok().body(user.getPromotionDetail());
	}
	
	// creating Promotion including file with userId
	@PutMapping("/user/promotion/{id}")
	public ResponseEntity<User> createPromotionByUserIdWithFile(@PathVariable(value = "id")int userId,
			@RequestParam(value="file") MultipartFile file ,
			@RequestParam(value="user") String userDetail) throws ResourceNotFoundException, IOException{
		ResponseEntity<User> userWithNewPromotion=service.getUserWithNewPromotion(userId,file,userDetail);
		return userWithNewPromotion;
	}
}
