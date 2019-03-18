/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.STOCK_ID;
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
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.service.impl.StockServiceImpl;

/**
 * The Stock Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class StockControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The StockService instance.
     */
    @Mock
    private  StockServiceImpl  stockService;
    
    @InjectMocks
    private StockController stockController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
   	public void init(){
   		MockitoAnnotations.initMocks(this);
   		mockMvc = MockMvcBuilders
   				.standaloneSetup(stockController)
   				.setControllerAdvice(new ExceptionHandlerController())				
   				.build();
   	}
    

  
    @Test
    public void getStock() throws Exception {
    	int id = STOCK_ID;
    	Stock stock = InventoryTestHelper.getStock();
    	stock.setId(id);
    	
    	when(stockService.get(anyLong())).thenReturn(stock);
    	
    	mockMvc.perform(get(API_VER +  "/stock/{id}" , id))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id", is(id)));

		verify(stockService, times(1)).get(anyLong());
    }
    
    @Test
    public void getStock_404_not_found() throws Exception {
    	int id = STOCK_ID;
    	
    	when(stockService.get(anyLong())).thenThrow(new EntityNotFoundException("entity of ID=" + id + " can not be found."));
    	mockMvc.perform(get(API_VER +  "/stock/{id}" , id))
    	.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.message", is("entity of ID=" + id + " can not be found.")));
    }

    @Test
    public void createStock() throws Exception {    	    
    	int id = STOCK_ID;
    	Stock stock = InventoryTestHelper.getStock();
    	Stock added = InventoryTestHelper.getStock();
    	added.setId(id);
    	
    	when(stockService.create(stock)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/stock")
				.contentType(APPLICATION_JSON_UTF8_VALUE)
				.content(InventoryTestHelper.asJsonString(stock)))
		.andExpect(status().isCreated());
    }
    
    @Test
    public void updateStock() throws Exception {       
    	int id = STOCK_ID;
    	Stock stock = InventoryTestHelper.getStock();
    	Stock updated = InventoryTestHelper.getStock();
    	updated.setId(id);
    	
    	when(stockService.update(id, stock)).thenReturn(updated);
    	
   	 	mockMvc.perform(put(API_VER + "/stock/{id}" , id)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(InventoryTestHelper.asJsonString(stock)))
                .andExpect(status().isOk());

    }
   
    @Test
    public void deleteStock() throws Exception {
    	
    	doNothing().when(stockService).delete(STOCK_ID);
    	mockMvc.perform(delete(API_VER + "/stock/{id}" , STOCK_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    
    }

}
