package com.mongale.ecommercecore.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mongale.ecommercecore.message.ApiResponse;
import com.mongale.ecommercecore.model.ImageFile;
import com.mongale.ecommercecore.repository.ImageFileRepository;

@Component
public class UploadImageFileServ {
	
	 @Autowired
	  private ImageFileRepository imageRepo;
	 
	 public ResponseEntity<?> uploadImageFile(@RequestParam("file") MultipartFile file) throws IOException{
		  
		  
		  if(file!=null) {
		   ImageFile img = new  ImageFile();
			
			img.setName(file.getOriginalFilename());
			img.setPic(file.getBytes());
			img.setType(file.getContentType());  
			
			imageRepo.save(img);
			
			return new ResponseEntity<>(new ApiResponse(true, "product image was successfully loaded!"), HttpStatus.OK);
		  }
		  
		   return new ResponseEntity<>(new ApiResponse(true,"product image is unsuccessfully! loaded "), HttpStatus.BAD_REQUEST);
	  }

}
