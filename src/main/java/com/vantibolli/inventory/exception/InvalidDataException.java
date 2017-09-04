/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.exception;

/**
 * This exception is thrown when an invalid date provide.
 * 
 * @author  mukhtiar.ahmed
 *
 * version 1.0
 */
public class InvalidDataException extends SystemException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3176392501022469536L;
	/**
	 * <p>
	 * This is the constructor of <code>InvalidDataException</code> class with message and cause arguments.
	 * </p>
	 *
	 * @param message the error message.
	 * @param cause the cause of the exception.
	 */
	public InvalidDataException(String message, Throwable exception) {
		super(message, exception);
		
	}

	/**
	 * <p>
	 * This is the constructor of <code>InvalidDataException</code> class with message and cause arguments.
	 * </p>
	 *
	 * @param message the error message.
	 */
	public InvalidDataException(String message) {
		super(message);
		
	}

	
	
	
	
	

}
