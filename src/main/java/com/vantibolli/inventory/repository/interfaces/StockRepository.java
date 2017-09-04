/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository.interfaces;

import java.util.List;

import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
/**
 * The Stock repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
public interface StockRepository extends GenericRepository<Stock> {
	
	/**
	 * Get Stock by item id and warehouse id.
	 * @param itemId the Item id.
	 * @param warehouseId the Warehosue id.
	 * @return Stock
	 */
	Stock findStockByItemIdAndWarehouseId(long itemId, long warehouseId) throws InventoryException ;
	
	/**
	 * Get Stock List of from given Item id and Country id. 
	 * @param itemId the Item id.
	 * @param countryId the Country id.
	 * @return List<Stock> 
	 * @throws InventoryException
	 */
	List<Stock> findStockByItemIdAndCountryId(long itemId, long countryId)  throws InventoryException ;	
	
	/**
	 *  Get Stock List of given Item id.
	 * @param itemId the Item id.
	 * @return  List<Stock> 
	 * @throws InventoryException
	 */
	List<Stock> findStockByItemId(long itemId)  throws InventoryException ;
	
	/**
	 * Update the Stock quantity by item id and warehouse id.
	 * @param itemId the Item id.
	 * @param warehouseId the Warehosue id.
	 * @param quantity the quantity	
	 * @throws InventoryException
	 */
	void updateByItemIdAndWarehouseId(long itemId,
			long warehouseId, int quantity) throws InventoryException;
	/**
	 * Update the Stock quantity by Item id and Country id.
	 * @param itemId the Item id.
	 * @param countryId the Country id.
	 * @param quantity the quantity
	 * @throws InventoryException
	 */
	void updateByItemIdAndCountryId(long itemId,
			long countryId, int quantity) throws InventoryException;
	/**
	 * Update the Stock quantity by Item id.
	 * @param itemId  the Item id.
	 * @param quantity the quantity
	 * @throws InventoryException
	 */
	void updateByItemId(long itemId, int quantity) throws InventoryException;

}
