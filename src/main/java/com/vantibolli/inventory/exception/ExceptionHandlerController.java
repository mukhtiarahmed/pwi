/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.dto.ErrorMessageDto;

/**
 * The exception handler.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * Handle EntityNotFoundException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(InventoryHelper.buildErrorResponse(exception, 404, exception.getMessage()));
    }

    /**
     * Handle InvalidDataException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<ErrorMessageDto> handleBadRequest(InvalidDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InventoryHelper.buildErrorResponse(exception, 400, exception.getMessage()));
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorMessageDto> handleBadRequest(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InventoryHelper.buildErrorResponse(exception, 400, InventoryHelper.INVALID_REQUEST_MESSAGE));
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessageDto> handleBadRequest(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InventoryHelper.buildErrorResponse(exception, 400, InventoryHelper.INVALID_REQUEST_MESSAGE));
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessageDto> handleBadRequest(HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InventoryHelper.buildErrorResponse(exception, 400, InventoryHelper.MISSING_REQUEST_JSON_MESSAGE));
    }
    
    /**
     * Handle RelocateMeException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequest(InventoryException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InventoryHelper.buildErrorResponse(exception, 400, exception.getMessage()));
    }

    /**
     * Handle all other runtime exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(InventoryHelper.buildErrorResponse(exception, 500,  InventoryHelper.ERROR_MESSAGE));
    }
    
    /**
     * Handle all other exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequest(SQLException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(InventoryHelper.buildErrorResponse(exception, 500, InventoryHelper.ERROR_MESSAGE));
    }

    /**
     * Handle all other exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequest(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(InventoryHelper.buildErrorResponse(exception, 500, InventoryHelper.ERROR_MESSAGE));
    }

}
