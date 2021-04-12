package com.example.application.service;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.exception.NullCheckException;
import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.Address;
import com.example.application.model.FileDetail;
import com.example.application.model.PromotionDetail;
import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	public UserRepository getUserRepository() {
		return repository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.repository = userRepository;
	}
	//register---------------------------------------------------
	public User getUserAfterSave(User user) 
	{
		String tempEmailId=user.getEmailId();
	if(tempEmailId!=null&& !"".equals(tempEmailId))
	{
		User userobj=repository.findByEmailId(tempEmailId);
		if(userobj!=null)
		{
			throw new ResourceNotFoundException("User Id "+ tempEmailId+" already exists");
		}
	}
	User userObj=null;
	user.setJoinedDate(new Date());
	user.setUpdatedDate(new Date());
	log.debug("dubug");
	System.out.println(user.getRoleType());
	if("viewer".equals(user.getRoleType()))
	{
		log.debug("dubug");
		Address userAddress=user.getUserAddress().get(0);
		userAddress.setAddressType("User Address");
		user.addUserAddress(userAddress);
		userObj=repository.save(user);	
	}
	else
	{
		log.debug("dubug");
	Address userAddress=user.getUserAddress().get(0);
	userAddress.setAddressType("User Address");
	Address shopAddress=user.getShopAddress().get(0);
	shopAddress.setAddressType("Shop Address");
	user.addUserAddress(userAddress);
	user.addShopAddress(shopAddress);
	userObj=repository.save(user);	
	}
		return userObj;
	}
//register by id-----------------------------
public User getUserForLoginById(int id, User user) {
	User existingUser =repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: '" + id+"'"));
	String tempEmailId=user.getEmailId();
	if(tempEmailId!=null&& !"".equals(tempEmailId))
	{
		User userobj=repository.findByEmailId(tempEmailId);
		if(userobj!=null)
		{
			throw new NullCheckException("User Id "+ tempEmailId+" already exists");
		}
	}
	User userObj=null;
	user.setJoinedBy(existingUser.getFirstName()+" "+existingUser.getLastName());
	userObj=repository.save(user);

	return userObj;
}
//-------------------------------------------
	public User saveUser(User user) {

		return repository.save(user);
	}

	public User userDetailsForLogin(User user){
		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User userObj = null;
		if (tempEmailId != null && tempPassword != null) {
			userObj = repository.findByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		if (userObj == null) {
			throw new NullCheckException("This Email '" + tempEmailId + "'  doesn't exists");
		}
		return userObj;
	}
	
	// creating Promotion including file with userId
	public ResponseEntity<User> getUserWithNewPromotion(int userId, MultipartFile file, String userDetail) throws ResourceNotFoundException,IOException {
		
		User user=new ObjectMapper().readValue(userDetail, User.class);
		User existingUser =repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		PromotionDetail promotion=user.getPromotionDetail().get(0);
		FileDetail fileDetail=new FileDetail();
		fileDetail.setFileName(file.getOriginalFilename());
		fileDetail.setPicByte(file.getBytes());
		fileDetail.setFileType(file.getContentType());
		promotion.addFile(fileDetail);
		promotion.setCreatedDate(new Date());
		promotion.setUpdatedDate(new Date());
		existingUser.addPromotion(promotion);
		final User updatedPromotion =  repository.save(existingUser);
		return ResponseEntity.ok(updatedPromotion);
	}
}
