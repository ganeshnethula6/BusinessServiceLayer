package com.example.application.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.model.PromotionDetail;
import com.example.application.model.FileDetail;
import com.example.application.repository.ClientPromotionRepository;
import com.example.application.repository.FileRepository;

@Service
public class ClientPromotionService {
	@Autowired
	private ClientPromotionRepository promotionRepository;
	@Autowired
	private FileRepository fileRepository;
	

	public ClientPromotionRepository getPromotionRepository() {
		return promotionRepository;
	}

	public void setPromotionRepository(ClientPromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	public FileDetail getImg(@RequestParam("imageFile")MultipartFile file) throws IOException {
		FileDetail img = new FileDetail(file.getOriginalFilename(), file.getContentType(),compressBytes(file.getBytes()));
				
		this.fileRepository.save(img);
		return img;
	}
	
	
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}

		return outputStream.toByteArray();
	}

	public PromotionDetail savePromotion(PromotionDetail user) {
	
		return this.promotionRepository.save(user);
	}

	public List<PromotionDetail> findUserByAddType(String addType) {
		
		return this.promotionRepository.findByAddType(addType);
	}

//	public PromotionDetail findUserById(int promotionId) {
//		PromotionDetail promotion=promotionRepository.findByUserId(promotionId);
//		
//		return promotion;
//	}


}
