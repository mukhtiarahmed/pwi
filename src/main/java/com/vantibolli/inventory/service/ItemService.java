/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service;

import java.util.List;

import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Item;

/**
 * The Item Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public interface ItemService extends GenericService<Item> {		
	
	/**
	 * Get Item quantity by Item id and Warehouse Id.
	 * @param itemId
	 * @param warehouseId
	 * @return ItemQuantityDto
	 * @throws InventoryException
	 */
	ItemQuantityDto getItemQuantityByWarehouseId(
			long itemId, long warehouseId) throws InventoryException;
	
	/**
	 * Get Item quantity list by Item id and Country Id.
	 * @param itemId the Item id.
	 * @param countryId the Country id.
	 * @return ItemQuantityDto
	 * @throws InventoryException
	 */
	List<ItemQuantityDto> getItemQuantityByCountryId(
			long itemId, long countryId) throws InventoryException;
	/**
	 * Get Item quantity list by Item id.
	 * @param itemId the Item id.
	 * @return List<ItemQuantityDto>
	 * @throws InventoryException
	 */
	List<ItemQuantityDto> getItemQuantityAllWarehouse(
			long itemId) throws InventoryException;
	

}
