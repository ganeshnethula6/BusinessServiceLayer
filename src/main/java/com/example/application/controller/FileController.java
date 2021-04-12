package com.example.application.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.application.exception.ResourceNotFoundException;
import com.example.application.model.FileDetail;
import com.example.application.service.FileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/online-advertisement-application")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@PostMapping("/promotion/upload")
	public BodyBuilder uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		FileDetail img = new FileDetail(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		fileService.saveFile(img);
		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping("/promotions/files")
	public ResponseEntity<List<FileDetail>> getPromotionDetailsById(){
		List<FileDetail> user = fileService.getFileRepository().findAll();
	return ResponseEntity.ok().body(user);
}
	@DeleteMapping("/promotions/files/{id}")
	public Map<String, Boolean> deleteFile(@PathVariable(value = "id") int fileId)
			throws ResourceNotFoundException {
		
		return fileService.getFileDeleteResponse(fileId);
	}
	
	// compress the image bytes before storing it in the database
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
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}

		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}

	

}
