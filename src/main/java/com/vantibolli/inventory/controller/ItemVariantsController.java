/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.annotations.LogMethod;
import com.vantibolli.inventory.dto.MessageDto;
import com.vantibolli.inventory.exception.ConfigurationException;
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.service.ItemVariantsService;

/**
 * The ItemVariants REST controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(API_VER + "/itemVariants")
public class ItemVariantsController {
	
	/**
     * The ItemVariantsService instance.
     */
    @Autowired
    private  ItemVariantsService  itemVariantsService;
    
    
    @Autowired
	private MessageSource messageSource;
    

    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {  
    	InventoryHelper.checkConfigNotNull(itemVariantsService, "itemVariantsService");
    	InventoryHelper.checkConfigNotNull(messageSource, "messageSource");
    }
    
    /**
     * Get a single ItemVariants.
     *
     * @param id the ItemVariants ID
     * @return the ItemVariants entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    public ItemVariants get(@PathVariable long id) throws InventoryException {
        return itemVariantsService.get(id);
    }

    /**
     * Create the ItemVariants.
     *
     * @param entity the ItemVariants entity.
     * @return the entity
     * @throws InvalidDataException if entity is null or not valid
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @LogMethod   
    public ItemVariants create(@RequestBody ItemVariants entity) throws InventoryException {    	    
        return itemVariantsService.create(entity);
    }
  

    /**
     * Update the ItemVariants.
     *
     * @param id the ItemVariants ID
     * @param entity the ItemVariants entity
     * @return the updated entity
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public ItemVariants update(@PathVariable long id, @RequestBody ItemVariants entity) throws InventoryException {       
        return itemVariantsService.update(id, entity);
    }

    /**
     * Delete the ItemVariants.
     *
     * @param id the ItemVariants ID
     * @return the MessageDto
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    @LogMethod  
    public MessageDto delete(@PathVariable long id) throws InventoryException {
    	itemVariantsService.delete(id);
    	return new MessageDto(messageSource.getMessage("delete.entity.success", new String[]{"ItemVariants"}, Locale.getDefault()));
    }

}
