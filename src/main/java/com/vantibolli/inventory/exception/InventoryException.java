/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.exception;

/**
 * The base exception for the application. Thrown if there is an error during CRUD operations.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
public class InventoryException extends BusinessException {

    /**
	 * The serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
     * <p>
     * This is the constructor of <code>RelocateMeException</code> class with message argument.
     * </p>
     *
     * @param message the error message.
     */
    public InventoryException(String message) {
        super(message);
    }

    /**
     * <p>
     * This is the constructor of <code>RelocateMeException</code> class with message and cause arguments.
     * </p>
     *
     * @param message the error message.
     * @param cause the cause of the exception.
     */
    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
