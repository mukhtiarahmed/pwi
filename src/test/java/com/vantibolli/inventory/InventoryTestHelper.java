package com.vantibolli.inventory;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.model.Brand;
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.model.ItemType;
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.model.Warehouse;

public class InventoryTestHelper {
	
	public static final int COUNTRY_ID = 3;
	public static final int BRAND_ID = 2;
	public static final int PRODUCT_ID = 3;
	public static final int ITEM_VARIANTS_ID = 6;
	public static final int WAREHOUSE_ID = 6;
	public static final int ITEM_ID = 7;
	public static final int STOCK_ID = 10;
	
	
	 public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	
	public static Brand getBrand(String brandName) {
		Brand brand = new Brand();
		brand.setName(brandName);
		brand.setcreateDate(new Date());
		return brand;
	}
	
	public static Country getCountry(String countryName) {
		Country country = new Country();
		country.setCountryName(countryName);
		country.setcreateDate(new Date());
		return country;
	}
	public static Item getItem(String productName,String brandName, ItemType itemType, String itemVariantSize) {
		Item item = new Item();
		item.setProduct(getProduct(productName, brandName, itemType));
		item.setItemVariants(getItemVariants(itemVariantSize));
		item.setcreateDate(new Date());
		return item;
	}
	public static ItemVariants getItemVariants(String size) {
		ItemVariants itemVariants = new ItemVariants();
		itemVariants.setSize(size);
		itemVariants.setcreateDate(new Date());
		return itemVariants;
	}
	
	public static Product getProduct(String productName,String brandName, ItemType itemType) {
		Product product = new Product();
		product.setName(productName);
		product.setBrand(getBrand(brandName));
		product.setItemType(itemType);
		product.setcreateDate(new Date());
		return product;
	}
	
	public static Stock getStock(String productName, String brandName,ItemType itemType, String itemVariantSize, String warehouseName, String countryName) {
		Stock stock = new Stock();
		stock.setItem(getItem(productName, brandName, itemType, itemVariantSize));
		stock.setWarehouse(getWarehouse(warehouseName, countryName));
		stock.setcreateDate(new Date());
		return stock;
	}
	
	public static Stock getDefaultStock() {
		Stock stock = new Stock();
		stock.setItem(getItem("Stock's Product", "Stock's Brand", ItemType.FINISHED_PRODUCT, "Stock's Size"));
		stock.setWarehouse(getWarehouse("Stock's Warehouse", "Stock's Country"));
		stock.setAvailableQuantity(20);
		stock.setInStock(10);
		stock.setInTransit(10);
		stock.setMinOrderquantity(2);
		stock.setQuantityPerBox(5);
		stock.setReorderPoint(5);
		stock.setcreateDate(new Date());
		return stock;
	}
	
	public static Warehouse getWarehouse(String warehouseName, String countryName) {
		Warehouse warehouse = new Warehouse();
		warehouse.setName(warehouseName);
		warehouse.setCountry(getCountry(countryName));
		warehouse.setcreateDate(new Date());
		return warehouse;
	}
	
	public static Warehouse getWarehouse() {
		Country country = new Country();
		country.setId(COUNTRY_ID);
		Warehouse warehouse = new Warehouse();
		warehouse.setName("Stock's Warehouse");
		warehouse.setCountry(country);
		warehouse.setcreateDate(new Date());
		return warehouse;
	}
	
	
	public static Item getItem() {
		Product product = new Product();
		product.setId(PRODUCT_ID);
		ItemVariants itemVariants = new ItemVariants();	
		itemVariants.setId(ITEM_VARIANTS_ID);
		Item item = new Item();
		item.setProduct(product);
		item.setItemVariants(itemVariants);
		item.setcreateDate(new Date());
		return item;
	}
	
	public static Product getProduct(String productName) {
		Brand brand = new Brand();
		brand.setId(BRAND_ID);
		Product product = new Product();
		product.setName(productName);
		product.setBrand(brand);
		product.setItemType(ItemType.FINISHED_PRODUCT);
		product.setcreateDate(new Date());
		return product;
	}
	
	public static Stock getStock() {		
		Item item = new Item();
		item.setId(ITEM_ID);
		Warehouse warehouse = new Warehouse();
		warehouse.setId(WAREHOUSE_ID);
		Stock stock = new Stock();
		stock.setItem(item);
		stock.setWarehouse(warehouse);
		stock.setAvailableQuantity(20);
		stock.setInStock(10);
		stock.setInTransit(10);
		stock.setMinOrderquantity(2);
		stock.setQuantityPerBox(5);
		stock.setReorderPoint(5);
		stock.setcreateDate(new Date());
		return stock;
	}
	
	public static ItemQuantityDto getItemQuantityDto(int quantity) {
		ItemQuantityDto dto = new ItemQuantityDto();
		dto.setQuantity(quantity);
		return dto;

	}
}
