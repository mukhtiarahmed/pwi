package com.vantibolli.inventory.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.dto.ProductDto;
import com.vantibolli.inventory.exception.ConfigurationException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.mapper.GeneralMapper;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.service.BrandService;
import com.vantibolli.inventory.service.ProductService;
/**
 * The Product Service.
 * @author mukhtiar.ahmed
 * version 1.0
 */
@Service
@Transactional
public class ProductServiceImpl extends BaseService<Product> implements ProductService {
	
	
	@Autowired
	private BrandService brandService;
	
	 /**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {  
    	InventoryHelper.checkConfigNotNull(brandService, "brandService");    	
    }
	
	@Override
	public Product create(Product entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");		
		InventoryHelper.checkNull(entity.getItemType(), "itemType");		
		InventoryHelper.checkNullOrEmpty(entity.getName(), "name");
		InventoryHelper.checkNull(entity.getBrand(), "brand");
		brandService.get(entity.getBrand().getId());
		getRepository().persist(entity);
		return getRepository().getByKey(entity.getId());
	}

	@Override
	public Product update(long id, Product entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");		
		InventoryHelper.checkNull(entity.getItemType(), "itemType");		
		InventoryHelper.checkNullOrEmpty(entity.getName(), "name");
		InventoryHelper.checkNull(entity.getBrand(), "brand");
		brandService.get(entity.getBrand().getId());
		Product oldEntity = get(id);
		oldEntity.setBrand(entity.getBrand());
		oldEntity.setItemType(entity.getItemType());
		oldEntity.setName(entity.getName());
		getRepository().persist(oldEntity);
		return getRepository().getByKey(id);
	}
	@Override
	public ProductDto getProductAllSizes(long productId) throws InventoryException {
		Product product = get(productId);
		return GeneralMapper.convertProductToProductDto(product);
	}

}
