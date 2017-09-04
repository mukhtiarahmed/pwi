/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service;

import java.util.List;

import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
/**
 * The Stock Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public interface StockService extends GenericService<Stock> {

	/**
	 * Get Stock list of by item it and country id.
	 * @param itemId the Item id
	 * @param countryId the Country id.
	 * @return List<Stock>
	 *  @throws InventoryException
	 */
	List<Stock> getByItemIdAndCountryId(long itemId, long countryId) throws InventoryException;
	
	/**
	 * Get Stock by item id and warehouse id.
	 * @param itemId the Item id.
	 * @param warehouseId the Warehouse id.
	 * @return Stock
	 *  @throws InventoryException
	 */
	Stock getByItemIdAndWarehouseId(long itemId, long warehouseId)throws InventoryException;
	
	/**
	 * Get Stock list of by item 
	 * @param itemId  the Item id
	 * @return List<Stock>
	 * @throws InventoryException
	 */
	List<Stock> getByItemId(long itemId) throws InventoryException;
	
	/**
	 * Update Stock available quantity  by item id and warehouse id.
	 * @param itemId  the Item id.
	 * @param warehouseId the Warehouse id.
	 * @param quantity the quantity.
	 * @throws InventoryException
	 */
	void updateByItemIdAndWarehouseId(long itemId,
			long warehouseId, int quantity) throws InventoryException;
	/**
	 * Update Stock available quantity  by item id and country id.
	 * @param itemId the Item id.
	 * @param countryId  the Country id.
	 * @param quantity the quantity.
	 * @throws InventoryException
	 */
	void updateByItemIdAndCountryId(long itemId,
			long countryId, int quantity) throws InventoryException;
	/**
	 * update Stock available quantity  by item id.
	 * @param itemId the Item id.
	 * @param quantity the quantity.
	 * @throws InventoryException
	 */
	void updateByItemId(long itemId, int quantity) throws InventoryException;
}
