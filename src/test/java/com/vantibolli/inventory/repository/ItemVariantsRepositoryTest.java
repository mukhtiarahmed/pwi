package com.vantibolli.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.repository.interfaces.ItemVariantsRepository;

public class ItemVariantsRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private ItemVariantsRepository repository; 
	
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
		ItemVariants entity = InventoryTestHelper.getItemVariants("New Size");
		repository.persist(entity);
		Assert.assertNotEquals(entity.getId(), 0);
		long countAfterInsert = repository.count();
		Assert.assertEquals(countAfterInsert , count + 1);
	}
	
	@Test
	public void update() throws InventoryException {
		long id = InventoryTestHelper.ITEM_VARIANTS_ID;
		ItemVariants entity = repository.getByKey(id);
		entity.setSize("Update Size");
		repository.persist(entity);
		ItemVariants updated = repository.getByKey(id);
		Assert.assertEquals(entity.getSize(), updated.getSize());
		
	}

	
	@Test
	public void delete() throws InventoryException  {	
		ItemVariants entity = InventoryTestHelper.getItemVariants("New Size");
		repository.persist(entity);
		long count = repository.count();
		repository.delete(entity);
		long countAfterDelete = repository.count();
		Assert.assertNotEquals(count, countAfterDelete);
		
	}
	
	@Test
	public void findAll() throws InventoryException {
		List<ItemVariants> entities= repository.findAll();
		long count = repository.count();		
		Assert.assertEquals(entities.size(), count);
		Assert.assertNotNull(entities);
		
	}

}
