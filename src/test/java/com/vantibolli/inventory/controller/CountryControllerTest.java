/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.COUNTRY_ID;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
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
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.service.impl.CountryServiceImpl;

/**
 * The Country Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The CountryService instance.
     */
    @Mock
    private  CountryServiceImpl  countryService;
    
    @InjectMocks
    private CountryController countryController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
   	public void init(){
   		MockitoAnnotations.initMocks(this);
   		mockMvc = MockMvcBuilders
   				.standaloneSetup(countryController)
   				.setControllerAdvice(new ExceptionHandlerController())				
   				.build();
   	}
    

  
    @Test
    public void getCountry() throws Exception {
    	int countryId = COUNTRY_ID;
    	Country country = InventoryTestHelper.getCountry("Pakistan");
    	country.setId(countryId);
    	when(countryService.get(anyLong())).thenReturn(country);
    	
    	mockMvc.perform(get(API_VER +  "/country/{id}" , countryId))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_VALUE))      
		.andExpect(jsonPath("$.id", is(countryId)));

		verify(countryService, times(1)).get(anyLong());
    }
    
    @Test
    public void getCountry_404_not_found() throws Exception {
    	int countryId = COUNTRY_ID;
    	
    	when(countryService.get(anyLong())).thenThrow(new EntityNotFoundException("entity of ID=" + countryId + " can not be found."));
    	
    	mockMvc.perform(get(API_VER +  "/country/{id}" , countryId))
    	.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_VALUE))      
		.andExpect(jsonPath("$.message", is("entity of ID=" + countryId + " can not be found.")));

		
    }

    @Test
    public void createCountry() throws Exception {    	    
    	int countryId = COUNTRY_ID;
    	Country country = InventoryTestHelper.getCountry("Pakistan");
    	
    	Country added = InventoryTestHelper.getCountry("Pakistan");
    	added.setId(countryId);
    	
    	when(countryService.create(country)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/country")
				.contentType(APPLICATION_JSON_VALUE)
				.content(InventoryTestHelper.asJsonString(country)))
		.andExpect(status().isCreated());
    }
    
    @Test
    public void updateCountry() throws Exception {       
    	int countryId = COUNTRY_ID;
    	Country country = InventoryTestHelper.getCountry("Pakistan");
    	
    	Country updated = InventoryTestHelper.getCountry("China");
    	updated.setId(countryId);
    	
    	when(countryService.update(COUNTRY_ID, country)).thenReturn(updated);
    	
   	 	mockMvc.perform(put(API_VER + "/country/{id}" , COUNTRY_ID)
                .contentType(APPLICATION_JSON_VALUE)
                .content(InventoryTestHelper.asJsonString(country)))
                .andExpect(status().isOk());             
    }
   
    @Test
    public void deleteCountry() throws Exception {
    
    	doNothing().when(countryService).delete(COUNTRY_ID);
    	mockMvc.perform(delete(API_VER + "/country/{id}" , COUNTRY_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}
