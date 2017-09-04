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
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.repository.interfaces.ItemVariantsRepository;
import com.vantibolli.inventory.service.impl.ItemVariantsServiceimpl;

public class ItemVariantsServiceTest extends AbstractServiceTest {

	@InjectMocks
	ItemVariantsServiceimpl itemVariantsService;
	
	@Mock
	ItemVariantsRepository itemVariantsRepository;
	
	@Test
	public void update() throws InventoryException {
		ItemVariants itemVariants = (ItemVariants)entityList.get(0);
		ItemVariants persisted = (ItemVariants)entityList.get(0);
		long id = 2;
		persisted.setId(id);
		doNothing().when(itemVariantsRepository).persist(persisted);
		when(itemVariantsRepository.getByKey(anyLong())).thenReturn(persisted);			
		ItemVariants result = itemVariantsService.create(itemVariants);
		Assert.assertNotNull(result);
		Assert.assertEquals(persisted.getId(), result.getId());
		verify(itemVariantsRepository, atLeastOnce()).persist(itemVariants);
	}
	
	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getItemVariants("Size: 123"));
		entityList.add(InventoryTestHelper.getItemVariants("Size: 456"));
	}
	
	

}
