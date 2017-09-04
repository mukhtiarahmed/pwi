package com.vantibolli.inventory.service;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.ItemType;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.repository.interfaces.ProductRepository;
import com.vantibolli.inventory.service.impl.ProductServiceImpl;

public class ProductServiceTest extends AbstractServiceTest {
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	BrandService brandService;
	
	@Test
	public void create() throws InventoryException {
		Product product = (Product) entityList.get(0);
		Product persisted = (Product) entityList.get(0);
		long id = 1;
		persisted.setId(id);
		when(brandService.get(anyLong())).thenReturn(persisted.getBrand());
		doNothing().when(productRepository).persist(persisted);
		when(productRepository.getByKey(anyLong())).thenReturn(persisted);
					
		Product result = productService.create(product);
		Assert.assertNotNull(result);
		Assert.assertEquals(persisted.getId(), result.getId());
		Assert.assertEquals(persisted.getName(), result.getName());
		Assert.assertEquals(persisted.getItemType(), result.getItemType());
		Assert.assertEquals(persisted.getBrand().getId(), result.getBrand().getId());
		verify(productRepository, atLeastOnce()).persist(product);
	}
	
	@Test
	public void update() throws InventoryException {
		Product product = (Product) entityList.get(0);
		Product persisted = (Product) entityList.get(0);
		long id = 1;
		persisted.setId(id);
		when(brandService.get(anyLong())).thenReturn(persisted.getBrand());
		when(productRepository.getByKey(anyLong())).thenReturn(persisted);
		when(productService.get(id)).thenReturn(persisted);
		doNothing().when(productRepository).persist(persisted);
		when(productRepository.getByKey(anyLong())).thenReturn(persisted);
					
		Product result = productService.update(id, product);
		Assert.assertNotNull(result);
		Assert.assertEquals(persisted.getId(), result.getId());
		Assert.assertEquals(persisted.getName(), result.getName());
		Assert.assertEquals(persisted.getItemType(), result.getItemType());
		Assert.assertEquals(persisted.getBrand().getId(), result.getBrand().getId());
		verify(productRepository, atLeastOnce()).persist(product);
		
	}

	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getProduct("Product 1", "Product's Brand 1", ItemType.COMPONENT));
		entityList.add(InventoryTestHelper.getProduct("Product 2", "Product's Brand 2", ItemType.PACKAGING_MATERIAL));
	}

}
