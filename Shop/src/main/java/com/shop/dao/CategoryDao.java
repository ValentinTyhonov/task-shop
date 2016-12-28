package com.shop.dao;

import java.util.List;

import com.shop.entity.Category;

public interface CategoryDao extends GeneralDao<Category> {
	
	List<Category> getByName(String name);

	Category getWithProducts(int id);
	
}
