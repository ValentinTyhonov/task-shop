package com.shop.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dao.CategoryDao;
import com.shop.dao.ProductDao;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.service.ProductService;
import com.shop.validator.Validator;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	@Qualifier("productValidator")
	private Validator validator;

	public void save(Product product, int categoryId, MultipartFile multipartFile) {
		productDao.saveAndFlush(product);
		
		saveCategory(product, categoryId);	
		saveImage(product, multipartFile);
		
		productDao.update(product);
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}

	public Product getOne(int id) {
		return productDao.getOne(id);
	}

	public void delete(int id) {
		productDao.delete(id);
	}

	public void update(Product product) {
		productDao.update(product);
	}

	public List<Product> getFromCategory(Category category) {
		return productDao.getFromCategory(category);
	}
	
	public Product getWithCategory(int id) {
		return productDao.getWithCategory(id);
	}

	public List<Product> liveSearch(String search) {
		return productDao.liveSearch(search);
	}

	public List<Product> getByPrice(int price) {
		return productDao.getByPrice(price);
	}

	public int[] getPriceLimit() {
		List<Product> products = productDao.getAll();
		int[] priceLimit = new int[2];
		priceLimit[0] = products.get(0).getPrice();
		priceLimit[1] = products.get(0).getPrice();
		
		for (Product product : products) {
			if(priceLimit[0] > product.getPrice()) {
				priceLimit[0] = product.getPrice();
			}
			if(priceLimit[1] < product.getPrice()) {
				priceLimit[1] = product.getPrice();
			}
		}	
		
		return priceLimit;
	}
	
	public void changeImage(Product product, MultipartFile multipartFile) {
		
		saveImage(product, multipartFile);
		
		productDao.update(product);
	}
	
	public void validation(Product product) throws Exception {
		validator.validate(product);
	}
	
	private void saveCategory(Product product, int categoryId) {
		Category category = categoryDao.getOne(categoryId);
		product.setCategory(category);
	}
	
	private void saveImage(Product product, MultipartFile multipartFile) {
		String path = System.getProperty("catalina.home") + "/shopimages/"
	            + product.getName() + "/" + multipartFile.getOriginalFilename();

		product.setImage("shopimages/" + product.getName() + "/" + multipartFile.getOriginalFilename());

	    File file = new File(path);

	    try {
	        file.mkdirs();
	        try {
	            FileUtils.cleanDirectory(new File(System.getProperty("catalina.home") + "/shopimages/" + product.getName() + "/"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        multipartFile.transferTo(file);
	    } catch (IOException e) {
	        System.out.println("error with file");
	    }
	}

}
