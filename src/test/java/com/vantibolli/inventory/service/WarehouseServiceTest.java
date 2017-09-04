package com.vantibolli.inventory.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.repository.interfaces.WarehouseRepository;
import com.vantibolli.inventory.service.impl.WarehouseServiceImpl;

public class WarehouseServiceTest extends AbstractServiceTest{
	
	@InjectMocks
	WarehouseServiceImpl warehouseService;
	
	@Mock
	WarehouseRepository warehouseRepository;
	
	@Mock
	CountryService countryService;
	
	@Test
	public void update() throws InventoryException {
		
	}

	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getWarehouse("Warehouse Pakistan", "Pakistan"));
		entityList.add(InventoryTestHelper.getWarehouse("Warehouse China", "China"));
	}
	
	
	
}
