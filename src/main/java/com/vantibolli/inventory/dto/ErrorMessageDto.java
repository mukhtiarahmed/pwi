/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.dto;

/**
 * The ErrorMessage dto class for sending error message.
 * @author mukhtiar.ahmed
 * version 1.0
 */
public class ErrorMessageDto implements ResponseDto {
	
	private String message;			
	
	private long timestamp;
	
	private int status;
	
	private String exception;
	
	/**
	 * status the status
	 */
	public long getStatus() {
		return status;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
	
}
