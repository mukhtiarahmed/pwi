/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.service;

import java.util.List;

import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;


/**
 * The generic service that expose the list method.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 * @param <T> the generic entity type
 */
public interface GenericListableService<T extends BaseEntity> extends GenericService <T> {

	 /**
     * List all entities.
     *
     * @return the entity list
     * @throws InventoryException if there is any error
     */
	  List<T> list() throws InventoryException;
}
