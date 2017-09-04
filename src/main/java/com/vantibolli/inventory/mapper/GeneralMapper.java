/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.dto.ProductDto;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.model.Warehouse;
/**
 * The General Mapper class
 * @author mukhtiar.ahmed
 *
 */
public class GeneralMapper {
	
	private GeneralMapper() {}

	/**
	 * Convert Product to ProductDto.
	 * @param product the product
	 * @return ProductDto
	 */
	public static ProductDto convertProductToProductDto(Product product) {
		if(product == null) {
			return null;
		}
		
		ProductDto dto = new ProductDto();
		dto.setName(product.getName());
		List<String> sizes = product.getItems().stream()
				.map(item -> item.getItemVariants().getSize())
						.collect(Collectors.toList());
		dto.setSizes(sizes);
		return dto;
	}
	
	/**
	 * Convert Stock to ItemDto.
	 * @param stock the Stock
	 * @return ItemDto
	 */
	public static ItemQuantityDto convertStockToItmDto(Stock stock) {
		if(stock == null) {
			return null;
		}
		ItemQuantityDto dto = new ItemQuantityDto();
		Item item = stock.getItem();
		Warehouse warehouse = stock.getWarehouse();
		dto.setName(item.getProduct().getName());
		dto.setSize(item.getItemVariants().getSize());
		dto.setWarehouse(warehouse.getName());
		dto.setCountry(warehouse.getCountry().getCountryName());
		dto.setQuantity(stock.getAvailableQuantity());
		return dto;
		
	}
	
	/**
	 * Convert Stock List to Item Dto list.
	 * @param stocks the Stock List.
	 * @return List<ItemDto>
	 */
	public static List<ItemQuantityDto> convertStockListToItmDtoList(List<Stock> stocks) {
		 List<ItemQuantityDto> list =  stocks.stream()
			.map(stock -> convertStockToItmDto(stock))
					.collect(Collectors.toList());
		 return list;
	}
	
}
