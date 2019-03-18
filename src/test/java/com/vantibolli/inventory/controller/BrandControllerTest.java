/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.BRAND_ID;
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
import com.vantibolli.inventory.model.Brand;
import com.vantibolli.inventory.service.impl.BrandServiceImpl;

/**
 * The Brand Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BrandControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The BrandService instance.
     */
    @Mock
    private  BrandServiceImpl  brandService;
    
    @InjectMocks
    private BrandController brandController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
   	public void init(){
   		MockitoAnnotations.initMocks(this);
   		mockMvc = MockMvcBuilders
   				.standaloneSetup(brandController)
   				.setControllerAdvice(new ExceptionHandlerController())				
   				.build();
   	}
    

  
    @Test
    public void getBrand() throws Exception {
    	int brandId = InventoryTestHelper.BRAND_ID;
    	Brand brand = InventoryTestHelper.getBrand("Brand");
    	brand.setId(brandId);
    	when(brandService.get(anyLong())).thenReturn(brand);
    	
    	mockMvc.perform(get(API_VER +  "/brand/{id}" , brandId))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id", is(brandId)));

		verify(brandService, times(1)).get(anyLong());
    	
    }
    
    @Test
    public void getBrand_404_not_found() throws Exception {
    	int brandId = InventoryTestHelper.BRAND_ID;
    	when(brandService.get(anyLong())).thenThrow(new EntityNotFoundException("entity of ID=" + brandId + " can not be found."));
    	
    	
    	mockMvc.perform(get(API_VER +  "/brand/{id}" , brandId))
    	.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.message", is("entity of ID=" + brandId + " can not be found.")));    

		
    }

    @Test
    public void createBrand() throws Exception {   
    	int brandId = InventoryTestHelper.BRAND_ID;
    	Brand brand = InventoryTestHelper.getBrand("Brand");
    	
    	Brand added = InventoryTestHelper.getBrand("Brand");
    	added.setId(brandId);
    	
    	when(brandService.create(brand)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/brand")
				.contentType(APPLICATION_JSON_UTF8_VALUE)
				.content(InventoryTestHelper.asJsonString(brand)))
		.andExpect(status().isCreated());
        
    }
    
    @Test
    public void updateBrand() throws Exception {   
    	
    	Brand brand = InventoryTestHelper.getBrand("Brand");
    	
    	Brand updated = InventoryTestHelper.getBrand("Updated Brand");
    	updated.setId(BRAND_ID);
    	
    	when(brandService.update(BRAND_ID, brand)).thenReturn(updated);
    	
   	 	mockMvc.perform(put(API_VER + "/brand/{id}" , BRAND_ID)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(InventoryTestHelper.asJsonString(brand)))
                .andExpect(status().isOk());             

   	
    }
   
    @Test
    public void deleteBrand() throws Exception {
    	doNothing().when(brandService).delete(BRAND_ID);
    	mockMvc.perform(delete(API_VER + "/brand/{id}" , BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    	
    }

}
