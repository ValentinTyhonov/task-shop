package com.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Category;
import com.shop.entity.Product;

public interface ProductService {
	
	void save(Product product, int categoryId, MultipartFile multipartFile);

	List<Product> getAll();

	Product getOne(int id);

    void delete(int id);
    
    void update(Product product);
    
    List<Product> getFromCategory(Category category);
    
    Product getWithCategory(int id);
    
    List<Product> liveSearch(String search);
    
    List<Product> getByPrice(int price);
    
    int[] getPriceLimit();
    
    void changeImage(Product product, MultipartFile multipartFile);
    
    void validation(Product product) throws Exception;
    
}
