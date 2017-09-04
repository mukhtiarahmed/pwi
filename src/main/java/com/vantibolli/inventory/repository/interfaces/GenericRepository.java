/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository.interfaces;

import java.util.List;

import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;
/**
 * The interface Abstract Parent  repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
public interface GenericRepository<T extends BaseEntity>{

	
	/**
	 * Get Entity by id.
	 * @param key the record id 
	 * @return the Entity
	 * @throws InventoryException if 
	 */
	T getByKey(long key) throws  InventoryException;

	/**
	 * To save entity
	 * @param entity the entity
	 * @throws InventoryException
	 */
	void persist(T entity) throws  InventoryException;
	/**
	 * To delete entity.
	 * @param entity the entity.
	 * @throws InventoryException
	 */
	void delete(T entity) throws  InventoryException;	
	
	/**
	 * Get all record specific entity
	 * @return the List of entity.
	 * @throws InventoryException
	 */
	List<T> findAll() throws  InventoryException;
	
	/**
	 * Count of specific entity.
	 * @return count
	 * @throws InventoryException
	 */
	long count() throws  InventoryException;
	
}
