package com.example.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileDetail {
	private int fileId;
	private String fileName;
	private String fileType;
	private byte[] picByte;
	public FileDetail() {
	
	}
	public FileDetail( String fileName, String fileType, byte[] picByte) {
		super();
	
		this.fileName = fileName;
		this.fileType = fileType;
		this.picByte = picByte;
	}
	
	@Id
	@Column(name = "file_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	@Column(name="file_name",nullable = false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="file_type",nullable = false)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Lob
	@Column(name="pic_byte",nullable = false,columnDefinition = "LONGBLOB")
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	

}
