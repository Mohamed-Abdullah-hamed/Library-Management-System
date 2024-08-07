package com.main.library.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionPojo {

	private LocalDateTime timeStamp;
	private List<String> message;
	private Integer statusCode;

	public ExceptionPojo() {
		super();
	}

	public ExceptionPojo(List<String> msg, Integer statusCode) {
		this.timeStamp = LocalDateTime.now();
		this.message = msg;
		this.statusCode = statusCode;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
