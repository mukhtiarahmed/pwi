package com.vantibolli.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.repository.interfaces.ProductRepository;

public class ProductRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private ProductRepository repository; 
	
	@Test
	public void getByKey() throws InventoryException {
		Assert.assertNotNull(repository.getByKey(1L));		
	}
	
	@Test(expectedExceptions = InventoryException.class)
	public void getByKeyFailure() throws InventoryException {
		Assert.assertNotNull(repository.getByKey(1211L));		
	}

	@Test
	public void insert() throws InventoryException  {
		long count = repository.count();	
		Product entity = InventoryTestHelper.getProduct("New Product");
		repository.persist(entity);
		Assert.assertNotEquals(entity.getId(), 0);
		long countAfterInsert = repository.count();
		Assert.assertEquals(countAfterInsert , count + 1);
	}
	
	@Test
	public void update() throws InventoryException {
		long id = InventoryTestHelper.PRODUCT_ID;
		Product entity = repository.getByKey(id);
		entity.setName("Updated Product");
		repository.persist(entity);
		Product updated = repository.getByKey(id);
		Assert.assertEquals(entity.getName(), updated.getName());
		
	}

	
	@Test
	public void delete() throws InventoryException  {	
		Product entity = InventoryTestHelper.getProduct("New Product");
		repository.persist(entity);
		long count = repository.count();
		repository.delete(entity);
		long countAfterDelete = repository.count();
		Assert.assertNotEquals(count, countAfterDelete);
		
	}
	
	@Test
	public void findAll() throws InventoryException {
		List<Product> entities= repository.findAll();
		long count = repository.count();		
		Assert.assertEquals(entities.size(), count);
		Assert.assertNotNull(entities);
		
	}

}
