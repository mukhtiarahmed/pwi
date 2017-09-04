package com.vantibolli.inventory.dto;

import java.util.List;


/**
 * 
 * The Product dto class for sending message.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */

public class ProductDto implements ResponseDto {
	
	private String name;
	
	private List<String> sizes;	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the sizes
	 */
	public List<String> getSizes() {
		return sizes;
	}

	/**
	 * @param sizes the sizes to set
	 */
	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

}
