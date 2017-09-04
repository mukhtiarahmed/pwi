/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vantibolli.inventory.dto.ErrorMessageDto;
import com.vantibolli.inventory.exception.ConfigurationException;
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
/**
 * The Helper class.
 * @author mukhtiar.ahmed
 *
 */
public final class InventoryHelper {

	/**
	 * private Constructor
	 */
	private InventoryHelper(){}
	
	/**
	 * Api Version
	 */
	public static final String API_VER = "/api/1.0";
	
	/**
	 * The object mapper.
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * <p>
	 * Represents the error message.
	 * </p>
	 */
	public static final String ERROR_MESSAGE = "Internal Server Error";
	/**
	 * <p>
	 * Represents the error message.
	 * </p>
	 */
	public static final String INVALID_REQUEST_MESSAGE = "HTTP URL or Method is not valid";
	/**
	 * <p>
	 * Represents the error message.
	 * </p>
	 */
	public static final String MISSING_REQUEST_JSON_MESSAGE = "Missing Request Input JSON";
		
	/**
	 * <p>
	 * Represents the entrance message.
	 * </p>
	 */
	private static final String MESSAGE_ENTRANCE = "Entering method %1$s.";

	/**
	 * <p>
	 * Represents the exit message.
	 * </p>
	 */
	private static final String MESSAGE_EXIT = "Exiting method %1$s.";

	/**
	 * <p>
	 * Represents the error message.
	 * </p>
	 */
	private static final String MESSAGE_ERROR = "Error in method %1$s. Details:";

	/**
	 * It checks whether a given object is null.
	 *
	 * @param object the object to be checked
	 * @param name the name of the object, used in the exception message
	 * @throws InvalidDataexception the exception thrown when the object is null
	 */
	public static void checkNull(Object object, String name) throws InvalidDataException {
		if (object == null) {
			throw new InvalidDataException(name + " is not provided");
		}
	}

	/**
	 * It checks whether a given string is null or empty.
	 *
	 * @param str the string to be checked
	 * @param name the name of the object, used in the exception message
	 * @throws InvalidDataexception the exception thrown when the object is null
	 */
	public static void checkNullOrEmpty(String str, String name) throws InvalidDataException {
		if (str == null || str.trim().isEmpty()) {
			throw new InvalidDataException("" + name + " is not provided");
		}
	}
	/**
	 * It checks whether the given list is null or empty.
	 * 
	 * @param list the list
	 * @param name the name of the object, used in the exception message
	 * @throws InvalidDataException  the exception thrown when the list is null or empty
	 */
	public static void checkNullOrEmpty(Collection <?> list, String name) throws InvalidDataException {
		if (list == null || list.isEmpty()) {
			throw new InvalidDataException("" + name + " is not provided");
		}
	}

	/**
	 * Check if the value is positive.
	 *
	 * @param value the value
	 * @param name the name
	 * @throws InvalidDataException if the value is not positive
	 */
	public static void checkPositive(long value, String name) throws InvalidDataException {
		if (value <= 0) {
			throw new InvalidDataException(name + " should be a positive value.");
		}
	}

	/**
	 * Check if the configuration state is true.
	 *
	 * @param state the state
	 * @param message the error message if the state is not true
	 * @throws ConfigurationException if the state is not true
	 */
	public static void checkConfigState(boolean state, String message) {
		if (!state) {
			throw new ConfigurationException(message);
		}
	}

	/**
	 * Check if the configuration is null or not.
	 *
	 * @param object the object
	 * @param name the name
	 * @throws ConfigurationException if the state is not true
	 */
	public static void checkConfigNotNull(Object object, String name) {
		if (object == null) {
			throw new ConfigurationException(String.format("%s should be provided", name));
		}
	}

	/**
	 * Check if an entity with a given key exists.
	 *
	 * @param id the long
	 * @param entity the entity object
	 * @throws EntityNotFoundException if the entity can not be found in DB
	 */
	public static void checkEntityExist(Object entity, long id) throws EntityNotFoundException {
		if (entity == null) {
			throw new EntityNotFoundException("entity of ID=" + id + " can not be found.");
		}
	}

	/**
	  * <p>
	 * Logs  message with paramNames and values  of parameters  at <code>DEBUG</code> level.
	 * </p> 
	 * @param logger  the log service.
	 * @param message  the message
	 * @param paramNames the names of parameters to log (not Null).
	 * @param params  the values of parameters to log (not Null).
	 */
	public static void logMessage(Logger logger, String message, String[] paramNames, Object[] params) {
		if (logger.isDebugEnabled()) {
			logger.debug( message + toString(paramNames, params));
		}
	}

	/**
	 * <p>
	 * Logs  message at <code>DEBUG</code> level.
	 * </p>
	 * @param logger  the log service.
	 * @param message  the message
	 */
	public static void logMessage(Logger logger, String message) {
		if (logger.isDebugEnabled()) {            
			logger.debug(message);
		}
	}
	
	/**
	 * <p>
	 * Logs  message at <code>WARN</code> level.
	 * </p>
	 * @param logger the log service.
	 * @param message  the message
	 */
	public static void logWarn(Logger logger, String message) {		           
			logger.warn(message);
	}	



	/**
	 * <p>
	 * Logs for entrance into public methods at <code>DEBUG</code> level.
	 * </p>
	 *
	 * @param logger the log service.
	 * @param signature the signature.
	 * @param paramNames the names of parameters to log (not Null).
	 * @param params the values of parameters to log (not Null).
	 */
	public static void logEntrance(Logger logger, String signature, String[] paramNames, Object[] params) {
		if (logger.isDebugEnabled()) {
			String msg = String.format(MESSAGE_ENTRANCE, signature) + toString(paramNames, params);
			logger.debug(msg);
		}
	}

	/**
	 * <p>
	 * Logs for exit from public methods at <code>DEBUG</code> level.
	 * </p>
	 *
	 * @param logger the log service.
	 * @param signature the signature of the method to be logged.
	 * @param value the return value to log.
	 */
	public static void logExit(Logger logger, String signature, Object value) {
		if (logger.isDebugEnabled()) {
			StringBuilder msg = new  StringBuilder(String.format(MESSAGE_EXIT, signature));
			if (value != null) {
				msg.append(" Output parameter : ").append(value);
			}
			logger.debug(msg.toString());
		}
	}

	/**
	 * <p>
	 * Logs the given exception and message at <code>ERROR</code> level.
	 * </p>
	 *
	 * @param <T> the exception type.
	 * @param logger the log service.
	 * @param signature the signature of the method to log.
	 * @param e the exception to log.
	 */
	public static <T extends Throwable> void logException(Logger logger, String signature, T e) {
		StringBuilder sw = new StringBuilder();
		sw.append(String.format(MESSAGE_ERROR, signature))
		.append(e.getClass().getName())
		.append(": ")
		.append(e.getMessage());
		Throwable cause = e.getCause();
		final String lineSeparator = System.getProperty("line.separator");
		while (null != cause) {
			sw.append(lineSeparator)
			.append("        Caused By: ")
			.append(cause.getClass().getName())
			.append(": ")
			.append(cause.getMessage());
			cause = cause.getCause();
		}
		
		if(e instanceof InventoryException) {
			logger.error(sw.toString());
		} else {
			logger.error(sw.toString(), e);
		}
		
	}

	/**
	 * <p>
	 * Converts the parameters to string.
	 * </p>
	 *
	 * @param paramNames the names of parameters.
	 * @param params the values of parameters.
	 * @return the string
	 */
	private static String toString(String[] paramNames, Object[] params) {
		StringBuilder sb = new StringBuilder(" Input parameters: {");
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(paramNames[i]).append(":").append(toString(params[i]));
			}
		}
		sb.append("}.");
		return sb.toString();
	}

	/**
	 * <p>
	 * Converts the object to string.
	 * </p>
	 *
	 * @param obj the object
	 * @return the string representation of the object.
	 */
	public static String toString(Object obj) {
		String result;
		try {
			if (obj instanceof HttpServletRequest) {
				result = "Spring provided servlet request";
			} else if (obj instanceof HttpServletResponse) {
				result = "Spring provided servlet response";
			} else if (obj instanceof ModelAndView) {
				result = "Spring provided model and view object";
			} else {
				result = MAPPER.writeValueAsString(obj);
			}

		} catch (JsonProcessingException e) {
			result = "The object can not be serialized by Jackson JSON mapper, error: " + e.getMessage();
		}
		return result;
	}
	/**
	 * Send the object in JSON response. 
	 * @param object the to
	 * @param response the HttpServletResponse response    
	 * @throws IOException
	 */
	public static void sendJsonResponse(Object object, HttpServletResponse response) throws IOException {
		
		  String jsonResponse;
		  try {
	        
			  jsonResponse =  MAPPER.writeValueAsString(object);
			  
			} catch (JsonProcessingException e1) {
				jsonResponse = "The object can not be serialized by Jackson JSON mapper";
			}
	        
	        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
	        response.getWriter().write(jsonResponse);
	        response.getWriter().close();
	}
	
	/**
     * Build the error response.
     *
     * @param e the exception
     * @param status the error status
     * @param message the error message
     * @return the error response
     */
    public static ErrorMessageDto buildErrorResponse(Throwable e, int status, String message) {        
        ErrorMessageDto dto = new ErrorMessageDto();
        dto.setTimestamp(new Date().getTime());
        dto.setStatus(status);
        dto.setException(e.getClass().getName());
        dto.setMessage(message);
        return dto;
    }
    

	/**
     * Build the error response.
     *
     * @param status the error status
     * @param message the error message
     * @return the error response
     */
    public static ErrorMessageDto buildErrorResponse(int status, String message) {
    	  ErrorMessageDto dto = new ErrorMessageDto();
          dto.setTimestamp(new Date().getTime());
          dto.setStatus(status);         
          dto.setMessage(message);
          return dto;
    }
 
}
