package com.vantibolli.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;

import com.vantibolli.inventory.model.BaseEntity;

public abstract class AbstractServiceTest {
	
	@Spy
	List<BaseEntity> entityList = new ArrayList<>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		entityList = new ArrayList<>();
		setList();
	}
	
	public abstract void setList();
	
	
}
