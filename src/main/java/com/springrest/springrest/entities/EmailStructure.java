package com.springrest.springrest.entities;

public class EmailStructure {
	String subject;
	String message;
	String from;
	String to;
	String fileUrl;
	public EmailStructure(String subject, String message, String from, String to,String fileUrL) {
		super();
		this.subject = subject;
		this.message = message;
		this.from = from;
		this.to = to;
		this.fileUrl=fileUrl;
	}
	public EmailStructure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSubject() {
		return subject;
	}
	
	public String getFileUrl() {
		return this.fileUrl;
	}
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl=fileUrl;
	}
	
	
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "EmailStructure [subject=" + subject + ", message=" + message + ", from=" + from + ", to=" + to
				+ ", fileUrl=" + fileUrl + "]";
	}
	
	
	
	
	
	
	

}
