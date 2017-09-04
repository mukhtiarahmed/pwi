/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.COUNTRY_ID;
import static com.vantibolli.inventory.InventoryTestHelper.ITEM_ID;
import static com.vantibolli.inventory.InventoryTestHelper.WAREHOUSE_ID;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.ExceptionHandlerController;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.repository.interfaces.ItemRepository;
import com.vantibolli.inventory.service.impl.ItemServiceImpl;
import com.vantibolli.inventory.service.impl.StockServiceImpl;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemControllerTest {

	private MockMvc mockMvc;	
	
	@Mock
	ItemRepository itemRepository;

	@Mock
	private  ItemServiceImpl  itemService;

	@Mock
	private StockServiceImpl stockService;

	@Mock
	MessageSource messageSource;

	@InjectMocks
	ItemController itemController;


	@BeforeClass
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(itemController)
				.setControllerAdvice(new ExceptionHandlerController())				
				.build();
	}

	// =================================== Get Item By ID ====================================
	@Test
	public void getItem() throws Exception {
		int  itemId =  ITEM_ID;
		Item item = InventoryTestHelper.getItem();
		item.setId(itemId);
		when(itemService.get(itemId)).thenReturn(item);
		mockMvc.perform(get(API_VER + "/item/{id}", itemId))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_VALUE))      
		.andExpect(jsonPath("$.id", is(itemId)));

		verify(itemService, times(1)).get(itemId);


	}


	@Test
	public void getItem_404_not_found() throws Exception {
		int  itemId = ITEM_ID;
		when(itemService.get(itemId)).thenThrow(new EntityNotFoundException("entity of ID=" + itemId + " can not be found."));
		mockMvc.perform(get(API_VER + "/item/{id}" , itemId))
		.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_VALUE))      
		.andExpect(jsonPath("$.message", is("entity of ID=" + itemId + " can not be found.")));    
	}

	@Test
	public void create() throws Exception{    	    
		Item item = InventoryTestHelper.getItem();

		Item added = InventoryTestHelper.getItem();
		added.setId(ITEM_ID);

		when(itemService.create(item)).thenReturn(added);

		mockMvc.perform(post(API_VER + "/item")
				.contentType(APPLICATION_JSON_VALUE)
				.content(InventoryTestHelper.asJsonString(item)))
		.andExpect(status().isCreated());  
		
	}


	@Test
	public void update() throws Exception {       
		Item item = InventoryTestHelper.getItem();

		Item updated = InventoryTestHelper.getItem();
		updated.setId(ITEM_ID);	
		when(itemService.update(ITEM_ID, item)).thenReturn(updated);
		
		   mockMvc.perform(put(API_VER + "/item/{id}" , ITEM_ID)
                .contentType(APPLICATION_JSON_VALUE)
                .content(InventoryTestHelper.asJsonString(item)))
                .andExpect(status().isOk());
	}

	@Test
	public void deleteItem() throws Exception {
		Item item = InventoryTestHelper.getItem();
		item.setId(ITEM_ID);
		when(itemService.get(ITEM_ID)).thenReturn(item);		
		doNothing().when(itemService).delete(ITEM_ID);
		
		mockMvc.perform(delete(API_VER + "/item/{id}" , ITEM_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void updateItemQuantityWarehouse() throws Exception{
		int quantity = 100;
		doNothing().when(stockService). updateByItemIdAndWarehouseId(ITEM_ID, WAREHOUSE_ID, quantity);
		
		   mockMvc.perform(post (API_VER + "/item/{id}/stock/warehouse/{warehouseId}" , ITEM_ID, WAREHOUSE_ID)               
                .param("quantity", String.valueOf(quantity)))
                .andExpect(status().isOk());
		
	}

	@Test
	public void updateItemQuantityCountryWarehouse() throws Exception { 
	int quantity = 100;
		doNothing().when(stockService). updateByItemIdAndCountryId(ITEM_ID, COUNTRY_ID, quantity);
		
		   mockMvc.perform(post (API_VER + "/item/{id}/stock/country/{countryId}" , ITEM_ID, WAREHOUSE_ID)               
                .param("quantity", String.valueOf(quantity)))
                .andExpect(status().isOk());
	}

	@Test 
	public void updateItemQuantityAllWareHouses() throws Exception {       
	int quantity = 100;
		doNothing().when(stockService).updateByItemId(ITEM_ID, quantity);
		
		   mockMvc.perform(post (API_VER + "/item/{id}/stock" , ITEM_ID)               
                .param("quantity", String.valueOf(quantity)))
                .andExpect(status().isOk());
	}

	@Test
	public  void getItemQuantityAllWarehouses() throws Exception {
		int quantity = 100;
		List<ItemQuantityDto> dtos = new ArrayList<>();
		dtos.add(InventoryTestHelper.getItemQuantityDto(quantity));
		dtos.add(InventoryTestHelper.getItemQuantityDto(quantity));
	
		when(itemService.getItemQuantityAllWarehouse(ITEM_ID)).thenReturn(dtos);
		
		    mockMvc.perform(get(API_VER + "/item/{id}/stock", ITEM_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].quantity", is(quantity)))
                .andExpect(jsonPath("$[1].quantity", is(quantity)));
	}

	@Test
	public void getItemQuantityWarehouse()throws Exception {       
		int quantity = 100;
		ItemQuantityDto dto = InventoryTestHelper.getItemQuantityDto(quantity);
		when(itemService.getItemQuantityByWarehouseId(ITEM_ID, WAREHOUSE_ID)).thenReturn(dto);	
		
		mockMvc.perform(get(API_VER + "/item/{id}/stock/warehouse/{warehouseId}", ITEM_ID, WAREHOUSE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.quantity", is(quantity)));
	}

	@Test
	public void getItemQuantityCountryWarehouses() throws Exception {       
		int quantity = 100;
		List<ItemQuantityDto> dtos = new ArrayList<>();
		dtos.add(InventoryTestHelper.getItemQuantityDto(quantity));
		dtos.add(InventoryTestHelper.getItemQuantityDto(quantity));
	
		when(itemService.getItemQuantityByCountryId(ITEM_ID, COUNTRY_ID)).thenReturn(dtos);
		
		   mockMvc.perform(get(API_VER + "/item/{id}/stock/country/{countryId}", ITEM_ID, COUNTRY_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].quantity", is(quantity)))
                .andExpect(jsonPath("$[1].quantity", is(quantity)));
	}

}
