/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.COUNTRY_ID;
import static com.vantibolli.inventory.InventoryTestHelper.WAREHOUSE_ID;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.ExceptionHandlerController;
import com.vantibolli.inventory.model.Warehouse;
import com.vantibolli.inventory.service.impl.WarehouseServiceImpl;

/**
 * The Warehouse Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class WarehouseControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The WarehouseService instance.
     */
    @Mock
    private  WarehouseServiceImpl  warehouseService;
    
    @InjectMocks
    private WarehouseController warehouseController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
   	public void init(){
   		MockitoAnnotations.initMocks(this);
   		mockMvc = MockMvcBuilders
   				.standaloneSetup(warehouseController)
   				.setControllerAdvice(new ExceptionHandlerController())				
   				.build();
   	}
    

  
    @Test
    public void getWarehouse() throws Exception {
    	int id = WAREHOUSE_ID;
    	Warehouse warehouse = InventoryTestHelper.getWarehouse();
    	warehouse.setId(id);
    	when(warehouseService.get(anyLong())).thenReturn(warehouse);
    	
    	mockMvc.perform(get(API_VER +  "/warehouse/{id}" , id))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id", is(id)));

		verify(warehouseService, times(1)).get(anyLong());
    }
    
    @Test
    public void getWarehouse_404_not_found() throws Exception {
    	int id = WAREHOUSE_ID;
    	
    	when(warehouseService.get(anyLong())).thenThrow(new EntityNotFoundException("entity of ID=" + id + " can not be found."));
    	
    	mockMvc.perform(get(API_VER +  "/warehouse/{id}" , id))
    	.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.message", is("entity of ID=" + id + " can not be found.")));
    }

    @Test
    public void createWarehouse() throws Exception {    	    
    	int id = WAREHOUSE_ID;
    	Warehouse warehouse = InventoryTestHelper.getWarehouse();
    	Warehouse added = InventoryTestHelper.getWarehouse();
    	added.setId(id);
    	
    	when(warehouseService.create(warehouse)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/warehouse")
				.contentType(APPLICATION_JSON_UTF8_VALUE)
				.content(InventoryTestHelper.asJsonString(warehouse)))
		.andExpect(status().isCreated());
    }
    
    @Test
    public void updateWarehouse() throws Exception {       
    	int id = WAREHOUSE_ID;
    	Warehouse warehouse = InventoryTestHelper.getWarehouse();
    	Warehouse updated = InventoryTestHelper.getWarehouse();
    	updated.setId(id);
    	
    	when(warehouseService.update(COUNTRY_ID, warehouse)).thenReturn(updated);
    	
   	 	mockMvc.perform(put(API_VER + "/warehouse/{id}" , id)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(InventoryTestHelper.asJsonString(warehouse)))
                .andExpect(status().isOk());
    }
   
    @Test
    public void deleteWarehouse() throws Exception {
    	
    	doNothing().when(warehouseService).delete(WAREHOUSE_ID);
    	mockMvc.perform(delete(API_VER + "/warehouse/{id}" , WAREHOUSE_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    	
    }

}
