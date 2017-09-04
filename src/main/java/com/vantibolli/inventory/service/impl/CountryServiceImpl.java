/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.service.CountryService;

/**
 * The Country Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Service
@Transactional
public class CountryServiceImpl extends BaseListableService<Country> implements CountryService {

	@Override
	@Transactional
	public Country update(long id, Country entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");
		InventoryHelper.checkNull(entity.getCountryName(), "countryName");
		Country oldEntity = get(id);
		oldEntity.setCountryName(entity.getCountryName());
		getRepository().persist(oldEntity);
		return getRepository().getByKey(id);
	}

	
}
