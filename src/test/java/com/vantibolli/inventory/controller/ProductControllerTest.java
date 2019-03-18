/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.PRODUCT_ID;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

import java.util.ArrayList;

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
import com.vantibolli.inventory.dto.ProductDto;
import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.ExceptionHandlerController;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.service.impl.ProductServiceImpl;

/**
 * The Product Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The ProductService instance.
     */
    @Mock
    private  ProductServiceImpl  productService;   
    
    @InjectMocks
    private ProductController productController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(productController)
				.setControllerAdvice(new ExceptionHandlerController())				
				.build();
	}
  
    @Test
    public void getProduct() throws Exception {
    	int  productId = PRODUCT_ID;
    	Product product = InventoryTestHelper.getProduct("New Product");
    	product.setId(productId);
    	
    	
    	when(productService.get(anyLong())).thenReturn(product);
    	
    	mockMvc.perform(get(API_VER + "/product/{id}" , productId))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id", is(productId)));

		

    }
    
    @Test
	public void getItem_404_not_found() throws Exception {
		int  productId = InventoryTestHelper.PRODUCT_ID;
		when(productService.get(productId)).thenThrow(new EntityNotFoundException("entity of ID=" + productId + " can not be found."));
		mockMvc.perform(get(API_VER + "/product/{id}" , productId))
		.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.message", is("entity of ID=" + productId + " can not be found.")));    
	}

    @Test
    public void createProduct() throws Exception {    	    
    	Product product = InventoryTestHelper.getProduct("New Product");
    	
    	Product added = InventoryTestHelper.getProduct("New Product");
    	added.setId(PRODUCT_ID);
    	
    	when(productService.create(product)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/product")
				.contentType(APPLICATION_JSON_UTF8_VALUE)
				.content(InventoryTestHelper.asJsonString(product)))
		.andExpect(status().isCreated());
    	
    }
    
    @Test
    public void updateProduct() throws Exception {       
    	
    	Product product = InventoryTestHelper.getProduct("New Product");
    	
    	Product updated = InventoryTestHelper.getProduct("New Product");
    	updated.setId(PRODUCT_ID);
    	when(productService.update(PRODUCT_ID, product)).thenReturn(updated);
    	
    	 mockMvc.perform(put(API_VER + "/product/{id}" , PRODUCT_ID)
                 .contentType(APPLICATION_JSON_UTF8_VALUE)
                 .content(InventoryTestHelper.asJsonString(product)))
                 .andExpect(status().isOk());
    	
    	
    }
   
    @Test
    public void deleteProduct() throws Exception {
    	doNothing().when(productService).delete(PRODUCT_ID);
    	mockMvc.perform(delete(API_VER + "/product/{id}" , PRODUCT_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    
    @Test
    public void getAllSizes() throws Exception {
    	
    	ProductDto dto = new ProductDto();
    	dto.setName("Product");
    	dto.setSizes(new ArrayList<>());
    	when(productService.getProductAllSizes(PRODUCT_ID)).thenReturn(dto);
    	mockMvc.perform(get(API_VER +  "/product/{id}/sizes" , PRODUCT_ID))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
    }


}
