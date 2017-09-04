package com.vantibolli.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.model.Warehouse;
import com.vantibolli.inventory.service.CountryService;
import com.vantibolli.inventory.service.WarehouseService;
/**
 * The Warehouse Service.
 * @author mukhtiar.ahmed
 * version 1.0
 */
@Service
@Transactional
public class WarehouseServiceImpl extends BaseService<Warehouse> implements WarehouseService {

	@Autowired
	private CountryService countryService;
	@Override
	public Warehouse update(long id, Warehouse entity) throws InventoryException {	
		InventoryHelper.checkNull(entity, "entity");
		Country country = entity.getCountry();
		InventoryHelper.checkNull(country, "country");
		countryService.get(country.getId());
		Warehouse oldEntity = get(id);
		oldEntity.setCountry(country);
		oldEntity.setName(entity.getName());
		getRepository().persist(entity);
		return getRepository().getByKey(id);
	}

}
