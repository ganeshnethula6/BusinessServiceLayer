package com.example.application.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.FileDetail;
import com.example.application.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;

	public FileRepository getFileRepository() {
		return fileRepository;
	}

	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	public void saveFile(FileDetail img) {
		this.fileRepository.save(img);
		
	}

	public Map<String, Boolean> getFileDeleteResponse(int fileId) {
		
		FileDetail file=fileRepository.findById(fileId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: '" + fileId + "'"));
		fileRepository.delete(file);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
