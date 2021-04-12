package com.example.application.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "promotion_details")
public class PromotionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int promotionId;
	private String addType;
	private String addTitle;
	private String addTag;
	private String addDesc;

//	@Column(columnDefinition = "MEDIUMBLOB")
//	private String image;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id", referencedColumnName = "promotionId")
	List<FileDetail> fileDetail = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id", referencedColumnName = "promotionId")
	List<Comment> comment = new ArrayList<>();

	public PromotionDetail() {

	}

	public PromotionDetail(String addType, String addTitle, String addTag, String addDesc, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.addType = addType;
		this.addTitle = addTitle;
		this.addTag = addTag;
		this.addDesc = addDesc;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getAddTitle() {
		return addTitle;
	}

	public void setAddTitle(String addTitle) {
		this.addTitle = addTitle;
	}

	public String getAddTag() {
		return addTag;
	}

	public void setAddTag(String addTag) {
		this.addTag = addTag;
	}

	public String getAddDesc() {
		return addDesc;
	}

	public void setAddDesc(String addDesc) {
		this.addDesc = addDesc;
	}

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_GB")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return new Date();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en_GB")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedDate() {
		return new Date();
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

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

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public void addComment(Comment comment) {
		this.comment.add(comment);
	}

	public void removeComment(Comment comment) {
		this.comment.remove(comment);
	}

}
