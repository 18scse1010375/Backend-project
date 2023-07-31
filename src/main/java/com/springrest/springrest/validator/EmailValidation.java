package com.springrest.springrest.validator;

import java.util.regex.*;

import org.springframework.stereotype.Component;

@Component
public class EmailValidation {
	
	public boolean isValidEmail(String email) {
		
		  String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
	        //Compile regular expression to get the pattern  
		  
		  
		  System.out.println("email are" +email);
	        Pattern pattern = Pattern.compile(regex);  
	        Matcher matcher=pattern.matcher(email);
	        
	       return  matcher.matches();
	}
}
	        
	       
