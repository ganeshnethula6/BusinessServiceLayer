package com.example.application.model;

import java.util.Date;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="user")
public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String roleType;
	private String gender;
	private String dob;
	private String mobileNumber;
	//private Address userAddress;
	//Shop details
	private String shopId;
	private String shopName;
	private String shopTag;
	private String shopCategory;
	//Shop Address
	//private Address shopAddress;
	private String emailId;
	private String password;
	private Date joinedDate;
	private String joinedBy;
	private Date updatedDate;
	private String updatedBy;
	List<PromotionDetail> promotionDetail=new ArrayList<>();
	List<FileDetail> fileDetail=new ArrayList<>();
	List<Address> userAddress=new ArrayList<>();
	List<Address> shopAddress=new ArrayList<>();
	
	

	public User()
	
	{
		
	}
	public User(String firstName, String lastName, String roleType, String gender, String dob, String mobileNumber,
			Address userAddress, String shopId, String shopName, String shopTag, String shopCategory,
			Address shopAddress, String emailId, String password, Date joinedDate, String joinedBy, Date updatedDate,
			String updatedBy) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleType = roleType;
		this.gender = gender;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		//this.userAddress = userAddress;
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopTag = shopTag;
		this.shopCategory = shopCategory;
		//this.shopAddress = shopAddress;
		this.emailId = emailId;
		this.password = password;
		this.joinedDate = joinedDate;
		this.joinedBy = joinedBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int id) {
		this.userId = id;
	}
@Column(name="first_name",nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name",nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="role_type",nullable=false)
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	@Column(name="gender",nullable=false)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="dob",nullable=false)
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Column(name="mobile_number",nullable=false)
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
//	@Column(name="user_address",nullable=true)
//	public Address getUserAddress() {
//		return userAddress;
//	}
//	public void setUserAddress(Address userAddress) {
//		this.userAddress = userAddress;
//	}
	@Column(name="shop_id",nullable=true)
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	@Column(name="shop_name",nullable=true)
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Column(name="shop_tag",nullable=true)
	public String getShopTag() {
		return shopTag;
	}
	public void setShopTag(String shopTag) {
		this.shopTag = shopTag;
	}
	@Column(name="shop_category",nullable=true)
	public String getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(String shopCategory) {
		this.shopCategory = shopCategory;
	}
//	@Column(name="shop_address",nullable=true)
//	public Address getShopAddress() {
//		return shopAddress;
//	}
//	public void setShopAddress(Address shopAddress) {
//		this.shopAddress = shopAddress;
//	}
	@Column(name="email_id",nullable=false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="joined_date",nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "en_GB")
	public Date getJoinedDate() {
		return new Date();
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	@Column(name="joined_by",nullable=true)
	public String getJoinedBy() {
		return joinedBy;
	}
	public void setJoinedBy(String joinedBy) {
		this.joinedBy = joinedBy;
	}
	@Column(name="updated_date",nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "en_GB")
	public Date getUpdatedDate() {
		return new Date();
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Column(name="updated_by",nullable=true)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName = "userId")
	public List<PromotionDetail> getPromotionDetail() {
		return promotionDetail;
	}
	public void setPromotionDetail(List<PromotionDetail> promotionDetail) {
		this.promotionDetail = promotionDetail;
	}
	public void addPromotion(PromotionDetail promotionDetail) {
		this.promotionDetail.add(promotionDetail);
	}
	public void removePromotion(PromotionDetail promotionDetail) {
		 this.promotionDetail.remove(promotionDetail);
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName = "userId")
	public List<FileDetail> getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(List<FileDetail> fileDetail) {
		this.fileDetail = fileDetail;
	}
	public void addFile(FileDetail fileDetail) {
		this.fileDetail.add(fileDetail);
	}
	public void removeFile(FileDetail fileDetail) {
		 this.fileDetail.remove(fileDetail);
	}
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName = "userId")
	public List<Address> getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(List<Address> userAddress) {
		this.userAddress = userAddress;
	}

	public void addUserAddress(Address userAddress) {
		this.userAddress.add(userAddress);
	}
	public void removeUserAddress(Address userAddress) {
		 this.userAddress.remove(userAddress);
	}
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName = "userId")
	public List<Address> getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(List<Address> shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	public void addShopAddress(Address shopAddress) {
		this.shopAddress.add(shopAddress);
	}
	public void removeShopAddress(Address shopAddress) {
		 this.shopAddress.remove(shopAddress);
	}
	
}
