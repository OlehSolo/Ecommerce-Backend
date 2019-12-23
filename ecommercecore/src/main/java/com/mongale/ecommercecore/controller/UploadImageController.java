package com.mongale.ecommercecore.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongale.ecommercecore.message.ApiResponse;
import com.mongale.ecommercecore.service.UploadImageFileServ;

@RestController
@RequestMapping("/api/picture")
@CrossOrigin(origins = "http://localhost:4200")
public class UploadImageController {
	
	@Autowired
	UploadImageFileServ uploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadPicture(@RequestParam("file") MultipartFile file) throws IOException
	{
		uploadService.uploadImageFile(file);
		
		return new ResponseEntity<>(new ApiResponse(true, "Picture is uploaded successfully"), HttpStatus.OK);
	}
	
	

}
