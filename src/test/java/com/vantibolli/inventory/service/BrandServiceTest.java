package com.vantibolli.inventory.service;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Brand;
import com.vantibolli.inventory.repository.interfaces.BrandRepository;
import com.vantibolli.inventory.service.impl.BrandServiceImpl;

public class BrandServiceTest extends AbstractServiceTest {

	@InjectMocks
	BrandServiceImpl brandService;
	
	@Mock
	BrandRepository brandRepository;
	
	@Test
	public void update() throws InventoryException {
		Brand brand = (Brand) entityList.get(0);
		Brand persisted = (Brand) entityList.get(0);
		long id = 2;
		persisted.setId(id);
		when(brandRepository.getByKey(id)).thenReturn(persisted);
		when(brandService.get(id)).thenReturn(persisted);				
		Brand resultBrand = brandService.update(id, brand);
		Assert.assertNotNull(resultBrand);
		Assert.assertEquals(resultBrand.getId(), persisted.getId());
		Assert.assertEquals(resultBrand.getName(), persisted.getName());
	}
	
	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getBrand("Brand A"));
		entityList.add(InventoryTestHelper.getBrand("Brand B"));
	}
	

}
