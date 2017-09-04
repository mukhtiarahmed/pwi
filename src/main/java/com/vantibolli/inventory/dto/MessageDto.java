/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.dto;


/**
 * 
 * The Message dto class for sending message.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
public class MessageDto implements ResponseDto {
	
	private String message;	
	
	public MessageDto(String message) {
		this.message = message;
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
	
	
}
