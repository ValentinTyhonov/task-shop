package com.shop.dao;

import java.util.List;

import com.shop.entity.Category;
import com.shop.entity.Product;

public interface ProductDao extends GeneralDao<Product> {
	
	List<Product> getFromCategory(Category category);
	
	Product getWithCategory(int id);

	List<Product> liveSearch(String search);
	
	List<Product> getByPrice(int price);
	
}
