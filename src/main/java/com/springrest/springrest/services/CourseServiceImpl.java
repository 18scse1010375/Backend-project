package com.springrest.springrest.services;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.dao.CourseDao;


@Service
public class CourseServiceImpl implements CourseService {
	List<Course>list;
	
	@Autowired(required=false)
	CourseDao dao ;   
	
	
//	public CourseServiceImpl() {
//		list=new ArrayList<>();
//		
//		list.add(new Course(110,"python","This is Python Course") );
//		list.add(new Course(111, "Java",   "This is java Course")  ); 
//		list.add(new Course(112,  "sql"  ,  "This is Sql  Course") );
//		
//		
//		
//	}
	
	
	public List<Course> getCourses(){
		
		return dao.findAll();
	     
		}
	
	
	
	
	public Course getCourse(long id) {
		Course c=null;
	      try
	      {
	    	 return dao.findById(id).get();
	      }
	      catch(Exception e)
	      {
	    	  System.out.println(e);
	    	  System.out.println("Course Not Found");
	    	  return c;
	      }

		
	}
	
	
	
	public Course deleteCourse(long id)
	{
	 Course c=dao.findById(id).get();
	 dao.delete(c);
	 return c;
	 
	 
	}
	
	
	
	public Course addCourse(Course course)
	{
		dao.save(course);
		return course;
//		list.add(course);
//		return course;
	}
	
	
	
	public Course updateCourse(Course course)
	{
		dao.save(course);
		return course;
	}
	
}
	

	
	
	