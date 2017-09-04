/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.Brand;
import com.vantibolli.inventory.repository.interfaces.BrandRepository;
/**
 * The Brand repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class BrandRepositoryImpl extends AbstractRepository<Brand> implements BrandRepository {

}
