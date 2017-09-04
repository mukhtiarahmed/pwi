/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.repository.interfaces.CountryRepository;
/**
 * The Country repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class CountryRepositoryImpl extends AbstractRepository<Country> implements CountryRepository {

}
