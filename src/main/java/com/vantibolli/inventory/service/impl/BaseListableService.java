/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.service.impl;

import java.util.List;

import com.vantibolli.inventory.annotations.LogMethod;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;
import com.vantibolli.inventory.service.GenericListableService;


/**
 * The base service exposing the list method.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 * @param <T> the generic entity type
 */
public abstract class BaseListableService<T extends BaseEntity> extends BaseService<T>
        implements GenericListableService<T> {

    /**
     * List all entities.
     *
     * @return the entity list
     * @throws RelocateMeException if there is any error
     */
    @Override
    @LogMethod
    public List<T> list() throws InventoryException {
        return repository.findAll();
    }

}
