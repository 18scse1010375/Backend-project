package com.springrest.springrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
	@Id
	long id;
	String title;
	String descrip;
	
	public Course(long id,String title,String descrip)
	{
		this.id=id;
		this.title=title;
		this.descrip=descrip;
	}
	
	
	public Course() {
		super();
	}
	
	
	


	public void setId(long id)
	{
		this.id=id;
	}
	public long getId() {
		return id;
	}
	
	
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle() {
		return title;
	}
	
	
	
	public void setDesc(String descrip)
	{
		this.descrip=descrip;
	}
	public String getDesc() {
		return descrip;
	}
	
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", descrip=" + descrip + "]";
	}
	
	
	

}
