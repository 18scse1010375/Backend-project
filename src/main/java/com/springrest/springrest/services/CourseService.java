package com.springrest.springrest.services;
import java.util.*;
import com.springrest.springrest.entities.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	public Course getCourse(long id);
	public Course deleteCourse(long id);
	public Course addCourse(Course course);
	public Course updateCourse(Course course);

}
