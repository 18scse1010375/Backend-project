package com.springrest.springrest.fileHandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
//	public final String dir1="C:\\Users\\SinghalArun(MJSR)\\Downloads\\springrest\\springrest\\src\\main\\resources\\static\\Images";

	 final public String dir1=new ClassPathResource("static/Images/").getFile().getAbsolutePath();

	 
	
	 
	
	
	
	public  FileUploadHelper() throws IOException{
		 
	 }
	 
	public void uploadFile(MultipartFile file)
	{
		 System.out.println("dir1 " +dir1  ) ;
		
		try {
			InputStream is=file.getInputStream();
			byte data[]=new byte[is.available()];
			is.read(data);
			
			FileOutputStream fos=new FileOutputStream(dir1 +File.separator+file.getOriginalFilename());
			fos.write(data);
			System.out.println("Try block workin......") ; 
		} 
		
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		 
//	Files.copy(file.getInputStream(), Paths.get(dir1 + File.separator + file.getOriginalFilename() )  ); 
//		
		
		
	}
	

}
