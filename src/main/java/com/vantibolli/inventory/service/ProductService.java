/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service;

import com.vantibolli.inventory.dto.ProductDto;
import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Product;

/**
 * The Product Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public interface ProductService extends GenericService<Product> {
	
	/**
	 * Get All Product Sizes.
	 * @param productId 
	 * @return ProductDto
	 * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
	 */	
	ProductDto getProductAllSizes(long productId) throws InventoryException;
	
}
