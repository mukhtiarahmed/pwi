/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.service.impl;




import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.annotations.LogMethod;
import com.vantibolli.inventory.exception.InvalidDataException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;
import com.vantibolli.inventory.repository.interfaces.GenericRepository;
import com.vantibolli.inventory.service.GenericService;



/**
 * This is a base class for services that provides basic CRUD capabilities.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 * @param <T> the generic entity type
 */

public abstract class BaseService<T extends BaseEntity> implements GenericService<T> {

    /**
     * The repository.
     */
    @Autowired 
    protected GenericRepository<T> repository;

   
    /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {
    	InventoryHelper.checkConfigNotNull(repository, "repository");     
    }

    /**
     * This method is used to retrieve an entity.
     *
     * @param id the Id
     * @return the entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws RelocateMeException if any other error occurred during operation
     */
    @Override
    @LogMethod
    public T get(long id) throws InventoryException {
    	InventoryHelper.checkPositive(id, "id");
        T entity = repository.getByKey(id);
        InventoryHelper.checkEntityExist(entity, id);
        return entity;
    }

    /**
     * This method is used to create an entity.
     *
     * @param entity the entity
     * @return the created entity
     * @throws InvalidDataException if entity is null or not valid
     * @throws RelocateMeException if any other error occurred during operation
     */
    @Override
    @LogMethod
    public T create(T entity) throws InventoryException {
    	InventoryHelper.checkNull(entity, "entity");
        repository.persist(entity);
        return repository.getByKey(entity.getId());
    }

    /**
     * This method is used to delete an entity.
     *
     * @param id the entity
     * @throws InvalidDataException if id is not positive
     * @throws EntityNotFoundException if the entity does not exist
     * @throws RelocateMeException if any other error occurred during operation
     */
    @Override
    @LogMethod
    public void delete(long id) throws InventoryException {
        T entity = repository.getByKey(id);
        repository.delete(entity);
    }
    
    public GenericRepository<T> getRepository() {
		return repository;
	}

}
