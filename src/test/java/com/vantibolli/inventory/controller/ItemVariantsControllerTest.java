/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.controller;

import static com.vantibolli.inventory.InventoryHelper.API_VER;
import static com.vantibolli.inventory.InventoryTestHelper.BRAND_ID;
import static com.vantibolli.inventory.InventoryTestHelper.ITEM_VARIANTS_ID;
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
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.service.impl.ItemVariantsServiceimpl;

/**
 * The ItemVariants Test controller.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemVariantsControllerTest {
	
	@Mock
	MessageSource messageSource;
	
	/**
     * The ItemVariantsService instance.
     */
    @Mock   
    private  ItemVariantsServiceimpl  itemVariantsService;
    
    @InjectMocks
    private ItemVariantsController itemVariantsController;
    
    private MockMvc mockMvc;	
    
    @BeforeClass
   	public void init(){
   		MockitoAnnotations.initMocks(this);
   		mockMvc = MockMvcBuilders
   				.standaloneSetup(itemVariantsController)
   				.setControllerAdvice(new ExceptionHandlerController())				
   				.build();
   	}
    

  
    @Test
    public void getItemVariants() throws Exception {
    	int id = InventoryTestHelper.ITEM_VARIANTS_ID;
    	ItemVariants itemVar = InventoryTestHelper.getItemVariants("Size");
    	itemVar.setId(id);
    	
    	when(itemVariantsService.get(anyLong())).thenReturn(itemVar);
    	
    	mockMvc.perform(get(API_VER +  "/itemVariants/{id}" , id))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id", is(id)));

		verify(itemVariantsService, times(1)).get(anyLong());
    }
    
    @Test
    public void getItemVariants_404_not_found() throws Exception {
    	int id = InventoryTestHelper.ITEM_VARIANTS_ID;
    	ItemVariants itemVar = InventoryTestHelper.getItemVariants("Size");
    	itemVar.setId(id);
    	
    	when(itemVariantsService.get(anyLong())).thenThrow(new EntityNotFoundException("entity of ID=" + id + " can not be found."));
    	
    	mockMvc.perform(get(API_VER +  "/itemVariants/{id}" , id))
    	.andExpect(status().isNotFound())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.message", is("entity of ID=" + id + " can not be found.")));
	
    }

    @Test
    public void createItemVariants() throws Exception {    	    
    	int id = InventoryTestHelper.ITEM_VARIANTS_ID;
    	ItemVariants itemVar = InventoryTestHelper.getItemVariants("Size");
    	
    	ItemVariants added = InventoryTestHelper.getItemVariants("Size");
    	added.setId(id);
    	
    	when(itemVariantsService.create(itemVar)).thenReturn(added);
    	
    	mockMvc.perform(post(API_VER + "/itemVariants")
				.contentType(APPLICATION_JSON_VALUE)
				.content(InventoryTestHelper.asJsonString(itemVar)))
		.andExpect(status().isCreated());
    }
    
    @Test
    public void updateItemVariants() throws Exception {       
    	int id = InventoryTestHelper.ITEM_VARIANTS_ID;
    	ItemVariants itemVar = InventoryTestHelper.getItemVariants("Size");
    	
    	ItemVariants updated = InventoryTestHelper.getItemVariants("Update Size");
    	updated.setId(id);
    	
    	when(itemVariantsService.update(ITEM_VARIANTS_ID, itemVar)).thenReturn(updated);
    	
   	 	mockMvc.perform(put(API_VER + "/itemVariants/{id}" , ITEM_VARIANTS_ID)
                .contentType(APPLICATION_JSON_VALUE)
                .content(InventoryTestHelper.asJsonString(itemVar)))
                .andExpect(status().isOk());  
    }
   
    @Test
    public void deleteItemVariants() throws Exception {
    	doNothing().when(itemVariantsService).delete(BRAND_ID);
    	mockMvc.perform(delete(API_VER + "/itemVariants/{id}" , BRAND_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

}
