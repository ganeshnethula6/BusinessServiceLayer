package com.example.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="replay")
public class Replay {
	
private int replayId;
private String replayMsg;
private String replayBy;
private Date replayDate;

public Replay() {
	
}
public Replay(String replayMsg, String replayBy, Date replayDate) {
	
	this.replayMsg = replayMsg;
	this.replayBy = replayBy;
	this.replayDate = replayDate;
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public int getReplayId() {
	return replayId;
}
public void setReplayId(int replayId) {
	this.replayId = replayId;
}
@Column(nullable = false)
public String getReplayMsg() {
	return replayMsg;
}
public void setReplayMsg(String replayMsg) {
	this.replayMsg = replayMsg;
}
public String getReplayBy() {
	return replayBy;
}
public void setReplayBy(String replayBy) {
	this.replayBy = replayBy;
}
@Column(nullable = false)
public Date getReplayDate() {
	return replayDate;
}
public void setReplayDate(Date replayDate) {
	this.replayDate = replayDate;
}

}
