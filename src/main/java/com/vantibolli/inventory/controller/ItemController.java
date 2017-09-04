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

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.annotations.LogMethod;
import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.dto.MessageDto;
import com.vantibolli.inventory.dto.ResponseDto;
import com.vantibolli.inventory.exception.ConfigurationException;
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.service.ItemService;
import com.vantibolli.inventory.service.StockService;

/**
 * The Item REST controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(API_VER + "/item")
public class ItemController {
	
	/**
     * The ItemService instance.
     */
    @Autowired
    private  ItemService  itemService;
    
    @Autowired
	private StockService stockService;
    
    @Autowired
	private MessageSource messageSource;
    

    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {  
    	InventoryHelper.checkConfigNotNull(itemService, "itemService");
    	InventoryHelper.checkConfigNotNull(messageSource, "messageSource");
    }
    
    /**
     * Get a single Item.
     *
     * @param id the Item id
     * @return the Item entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    public Item get(@PathVariable long id) throws InventoryException {
        return itemService.get(id);
    }

    /**
     * Create the Item.
     *
     * @param entity the Item entity.
     * @return the entity
     * @throws InvalidDataException if entity is null or not valid
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @LogMethod   
    public Item create(@RequestBody Item entity) throws InventoryException {    	    
        return itemService.create(entity);
    }
  

    /**
     * Update the Item.
     *
     * @param id the Item id
     * @param entity the Item entity
     * @return the updated entity
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public Item update(@PathVariable long id, @RequestBody Item entity) throws InventoryException {       
        return itemService.update(id, entity);
    }

    /**
     * Delete the Item.
     *
     * @param id the Item id
     * @return the MessageDto 
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    @LogMethod  
    public MessageDto delete(@PathVariable long id) throws InventoryException {
    	itemService.delete(id);
    	return new MessageDto(messageSource.getMessage("delete.entity.success", new String[]{"Item"}, Locale.getDefault()));
    }
     
    /**
     * Update the Item available quantity in given warehouse. 
     * @param id the Item id
     * @param warehouseId the Warehouse id.
     * @param quantity the item available quantity to update.
     * @return the Message
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
     
    @RequestMapping(value = "/{id}/stock/warehouse/{warehouseId}", method = POST, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public ResponseDto updateItemQuantityWarehouse(@PathVariable long id, @PathVariable long warehouseId,
    		@RequestParam(required = true) Integer quantity) throws InventoryException {   
    	stockService.updateByItemIdAndWarehouseId(id, warehouseId, quantity);
    	return new MessageDto(messageSource.getMessage("update.item.quantity.success", new String[]{}, Locale.getDefault()));
    }
    
    /**
     * Update the Item available quantity in given country warehouses. 
     * @param id the Item id
     * @param countryId the Country id
     * @param quantity the item available quantity to update.
     * @return the Message
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
     
    @RequestMapping(value = "/{id}/stock/country/{countryId}", method = POST, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public ResponseDto updateItemQuantityCountryWarehouse(@PathVariable long id, @PathVariable long countryId,
    		@RequestParam(required = true) Integer quantity) throws InventoryException { 
    	stockService.updateByItemIdAndCountryId(id, countryId, quantity);
    	return new MessageDto(messageSource.getMessage("update.item.quantity.success", new String[]{}, Locale.getDefault()));
    }
    
    /**
     * Update the Item available quantity in all warehouses. 
     * @param id the Item id
     * @param quantity the item available quantity to update.
     * @return the Message
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
     
    @RequestMapping(value = "/{id}/stock", method = POST, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public ResponseDto updateItemQuantityAllWareHouses(@PathVariable long id, @RequestParam(required = true) Integer quantity) throws InventoryException {       
    	stockService.updateByItemId(id, quantity);
    	return new MessageDto(messageSource.getMessage("update.item.quantity.success", new String[]{}, Locale.getDefault()));
    }
    
    /**
     * Get the Item available quantity in all warehouses.
     *
     * @param id the Item id
     * @return the Item entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    @RequestMapping(value = "/{id}/stock", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod
    public  List<ItemQuantityDto> getItemQuantityAllWarehouses(@PathVariable long id) throws InventoryException {
        return itemService.getItemQuantityAllWarehouse(id);
    }
    
    /**
     * Get the Item available quantity in given warehouse. 
     * @param id the Item id
     * @param warehouseId the Warehouse id.
     * @param quantity the item available quantity to update.
     * @return ItemQuantityDto
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
     
    @RequestMapping(value = "/{id}/stock/warehouse/{warehouseId}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public ItemQuantityDto getItemQuantityWarehouse(@PathVariable long id, @PathVariable long warehouseId) throws InventoryException {       
        return itemService.getItemQuantityByWarehouseId(id, warehouseId);
    }

    /**
     * Get the Item available quantity in given country warehouses. 
     * @param id the Item id
     * @param countryId the Country id   
     * @return List<ItemQuantityDto>
     * @throws InvalidDataException if id is not positive
     * @throws InventoryException if any other error occurred during operation
     */
     
    @RequestMapping(value = "/{id}/stock/country/{countryId}", method = GET, produces = APPLICATION_JSON_VALUE)
    @LogMethod   
    public List<ItemQuantityDto> getItemQuantityCountryWarehouses(@PathVariable long id,
    		@PathVariable long countryId) throws InventoryException {       
        return itemService.getItemQuantityByCountryId(id, countryId);
    }
  
    
    


}
