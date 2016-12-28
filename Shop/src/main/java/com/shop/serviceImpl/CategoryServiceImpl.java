package com.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.service.CategoryService;
import com.shop.validator.Validator;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;

	public void save(Category category) {
		categoryDao.save(category);
	}

	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	public Category getOne(int id) {
		return categoryDao.getOne(id);
	}

	public void delete(int id) {
		
		Category category = categoryDao.getWithProducts(id);
		
		for (Product product : category.getProducts()) {
			product.setCategory(null);
			productDao.save(product);
		}
		
		categoryDao.delete(id);
	}

	public void update(Category category) {
		categoryDao.update(category);
	}

	public List<Category> getByName(String name) {
		return categoryDao.getByName(name);
	}

}
