package com.shop.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dao.CategoryDao;
import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.entity.Category;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.service.ProductService;
import com.shop.validator.Validator;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	@Qualifier("productValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("imageValidator")
	private Validator imgValidator;

	public void save(Product product, int categoryId, MultipartFile multipartFile) throws Exception {
		imgValidator.validate(multipartFile);
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
	
	public void changeImage(Product product, MultipartFile multipartFile) throws Exception {
		imgValidator.validate(multipartFile);
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

	public void addToCart(int userId, int productId) {
		User user = userDao.getOne(userId);
		Product product = productDao.getOne(productId);
		
		try {
			Order orderExist = orderDao.getNotPaidByUser(user);
			List<Product> products = orderExist.getProducts();
			products.add(product);
			orderExist.setProducts(products);
			orderDao.update(orderExist);
		} catch(NoResultException e) {
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			Order orderNew = new Order(false);
			orderNew.setUser(user);
			orderNew.setProducts(products);	
			orderDao.save(orderNew);
		}
		
	}

	public void setCategory(Product product, int categoryId) {
		product.setCategory(categoryDao.getOne(categoryId));
	}

	public void setSameImage(int id, Product product) {
		product.setImage(productDao.getOne(id).getImage());
	}

}
