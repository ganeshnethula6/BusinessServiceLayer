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

@Entity
@Table(name="promotion_comments")
public class Comment {
	
private int commentId;
private String comment;
private String commentBy;
private Date createdDate;
List<Replay> replay=new ArrayList<>();

public Comment() {
	
}
public Comment(String comment, String commentBy, Date createdDate) {
	super();
	this.comment = comment;
	this.commentBy = commentBy;
	this.createdDate = createdDate;
}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public int getCommentId() {
	return commentId;
}
public void setCommentId(int commentId) {
	this.commentId = commentId;
}
@Column(name="comment" ,nullable = false)
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
@Column(name="comment_by" ,nullable = true)
public String getCommentBy() {
	return commentBy;
}
public void setCommentBy(String commentBy) {
	this.commentBy = commentBy;
}
@Column(name="comment_date" ,nullable = true)
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name="comment_id",referencedColumnName = "commentId")
public List<Replay> getReplay() {
	return replay;
}
public void setReplay(List<Replay> replay) {
	this.replay = replay;
}

public void addReplay(Replay replay)
{
	this.replay.add(replay);
}
public void removeReplay(Replay replay)
{
	this.replay.remove(replay);
}	
}
