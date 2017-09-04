/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Brand;
import com.vantibolli.inventory.service.BrandService;

/**
 * The Brand Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Service
public class BrandServiceImpl extends BaseService<Brand> implements BrandService {
	

	@Override
	@Transactional
	public Brand update(long id, Brand entity) throws InventoryException {		
		InventoryHelper.checkNull(entity, "entity");
		InventoryHelper.checkNull(entity.getName(), "name");
		Brand  oldEntity = get(id);
		oldEntity.setName(entity.getName());
		getRepository().persist(oldEntity);
		return getRepository().getByKey(id);
	}
	
	

}
