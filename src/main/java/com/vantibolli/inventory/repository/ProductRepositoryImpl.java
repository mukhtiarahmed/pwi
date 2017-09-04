/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.repository.interfaces.ProductRepository;

/**
 * The Product repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class ProductRepositoryImpl extends AbstractRepository<Product> implements ProductRepository {

}
