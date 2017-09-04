/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.service.ItemVariantsService;

/**
 * The ItemVariants Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Service
@Transactional
public class ItemVariantsServiceimpl  extends BaseListableService<ItemVariants> implements ItemVariantsService {

	@Override
	public ItemVariants update(long id, ItemVariants entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");
		InventoryHelper.checkNull(entity.getSize(), "size");
		ItemVariants oldEntity = get(id);
		oldEntity.setSize(entity.getSize());
		getRepository().persist(oldEntity);
		return  get(id);
	}
	
	

}
