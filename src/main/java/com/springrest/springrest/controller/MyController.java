package com.springrest.springrest.controller;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.EmailStructure;
import com.springrest.springrest.fileHandle.FileUploadHelper;
import com.springrest.springrest.services.*;
import com.springrest.springrest.validator.EmailValidation;

@RestController
public class MyController {
	   @Autowired(required=false)
	  CourseService service;
	   @Autowired (required=false)
	   FileUploadHelper helper;
	   
	   @Autowired(required=false)
	   EmailSending obj;
	   
	   @Autowired(required=false)
	     EmailValidation validator;
	
	@GetMapping("/home")
	public String home() {
		return "Home Page" ; 
	}
	
	
	
	
	
	
	@GetMapping("/courses")
	
	public ResponseEntity<List<Course>> getCourses()
	{
		try {
			return  ResponseEntity.status(HttpStatus.OK).body(service.getCourses()) ;		
			}
		
	
		catch(Exception e){
			System.out.println("exception e " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null) ;
			
			}
		
		
		
	}
	
	
	
	@GetMapping("/courses/{courseId}") 
	
	   public  Course getCourse(@PathVariable String courseId)
	   {
		 try
		 {
			 return service.getCourse(Long.valueOf(courseId)) ;
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 System.out.println("Invalid Course id");
			 return null;
			 
		 }
		
	   }
	
	@GetMapping("/form")
	public String openForm() {
		return "form";
	}
	
	
	@DeleteMapping("/courses/{courseId}")
	
	   public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId)
	   {
		try
		{
			Course c=service.deleteCourse(Long.valueOf(courseId));
			System.out.println("Course has been successfully deleted " +c );
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	   }
	
	@PostMapping("/courses")
	
	public ResponseEntity<Course>  addCourse(@RequestBody Course course)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addCourse(course));
	}
	@PutMapping("/courses") 
	
	public Course updateCourse(@RequestBody Course course)
	{
		return service.updateCourse(course);
	}
	
	
	
	@PostMapping("/email-send")
	
	public ResponseEntity<String> sendEmaill(@RequestBody EmailStructure structure)
	{
		
	  System.out.println(structure);
	  if(!validator.isValidEmail(structure.getFrom()) ) {
		  
		  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Sender Email are not correct");
		  
	  }
	  
	  
      if(!validator.isValidEmail(structure.getTo()) ) {
		  
		  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Recepient Email are not correct");
		  
	  }
	  
	  
	  
	  
	  
	 
	  
	  try {
		 boolean p= obj.sendEmail(structure.getFrom(), structure.getTo(), structure.getSubject(), structure.getMessage(),structure.getFileUrl());
		 System.out.println(p);
		 
		return (p== true) ? ResponseEntity.ok("Email Sent")  : ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Sending failed..Please try Again");
		 
		   }
	  
	  
	   catch(Exception e) {
		   System.out.println(e);
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service classs object is not created...");
	   }
	}
	
	
	@PostMapping("/file-upload")
	
	public ResponseEntity<String> fileUploading( @RequestParam("file") MultipartFile file   ){
		System.out.println("file is Uploading...");
		System.out.println(file.getContentType());
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
		}
		
		if(!file.getContentType().equals("application/pdf") &&  !file.getContentType().equals("image/jpeg" )       )
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Must Contain PDF  fILE or JPEG File");
		}
		
		if(file.getSize()>5000000)
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("File size can't exceed 5 mb");
		}
		
		
			
		
		
		
		
		else {
			try {
				System.out.println("File Size"  + file.getSize());
				helper.uploadFile(file);
				System.out.println("path="+ServletUriComponentsBuilder.fromCurrentContextPath().path("/Images/").path(file.getOriginalFilename()).toUriString() );
		return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString() )  ;  
				
				
//				return ResponseEntity.status(HttpStatus.ACCEPTED).body("File has been uploaded successfully");
	
			}
			
			catch(Exception e) {
				System.out.println("error:" + e);
				return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Helper class object is not able to create due to annotation problem");
				
			}
			
			
		}
		
		
		
		
	}
	
	

}
