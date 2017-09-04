/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.repository.interfaces.ItemRepository;

/**
 * The Item repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class ItemRepositoryImpl extends AbstractRepository<Item> implements ItemRepository {

}
