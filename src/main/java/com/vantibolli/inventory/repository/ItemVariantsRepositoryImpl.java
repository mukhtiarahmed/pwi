/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.repository.interfaces.ItemVariantsRepository;
/**
 * The ItemVariants repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class ItemVariantsRepositoryImpl extends AbstractRepository<ItemVariants> implements ItemVariantsRepository {

}
