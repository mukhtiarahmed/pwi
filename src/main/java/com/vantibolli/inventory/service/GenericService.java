/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.service;

import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;

/**
 * The generic service. Served as a base interface for CRUD operations.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 * @param <T> the generic entity type
 */
public interface GenericService<T extends BaseEntity> {

    /**
     * This method is used to retrieve an entity.
     *
     * @param id the Id
     * @return the entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    T get(long id) throws InventoryException;

    /**
     * This method is used to create an entity.
     *
     * @param entity the entity
     * @return the created entity
     * @throws InvalidDataException if entity is null or not valid
     * @throws InventoryException if any other error occurred during operation
     */
    T create(T entity) throws InventoryException;

    /**
     * This method is used to update an entity.
     *
     * @param id the id
     * @param entity the entity
     * @return the updated entity
     * @throws InvalidDataException if entity is null or not valid, or id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    T update(long id, T entity) throws InventoryException;

    /**
     * This method is used to delete an entity.
     *
     * @param id the entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws InventoryException if any other error occurred during operation
     */
    void delete(long id) throws InventoryException;

  
}
