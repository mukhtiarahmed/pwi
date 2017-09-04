/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.Warehouse;
import com.vantibolli.inventory.repository.interfaces.WarehouseRepository;
/**
 * The Warehouse repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class WarehouseRepositoryImpl extends AbstractRepository<Warehouse> implements WarehouseRepository{

}
