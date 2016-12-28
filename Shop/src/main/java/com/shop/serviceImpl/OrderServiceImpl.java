package com.shop.serviceImpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.dao.UserDao;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;

	public void save(Order order) {
		orderDao.save(order);
	}

	public List<Order> getAll() {
		return orderDao.getAll();
	}

	public Order getOne(int id) {
		return orderDao.getOne(id);
	}

	public void delete(int id) {
		orderDao.delete(id);
	}

	public void update(Order order) {
		orderDao.update(order);
	}

	public Order getNotPaidByUser(User user) {
		return orderDao.getNotPaidByUser(user);
	}

	@Transactional
	public void placeOrder(Order order, int total) {
		order.setTotal(total);
		order.setPaid(true);
		orderDao.update(order);
	}

	public int totalPrice(List<Product> products) {
		int totalPrice = 0;
		for (Product product : products) {
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}

	public void removeFromCart(int userId, int productId) {
		Order order = orderDao.getNotPaidByUser(userDao.getOne(userId));
		List<Product> products = order.getProducts();
				
		Iterator<Product> iterator = products.listIterator();
		while (iterator.hasNext()) {
			if(iterator.next().getId() == productId) {
				iterator.remove();
				break;
			}
		}

		order.setProducts(products);
		orderDao.update(order);
	}

	public String listOfBoughtProducts(Order order) {
		String list = "";
		for (Product product : order.getProducts()) {
			list += product.getName() + " $" + product.getPrice() + "\n";
		}
		return list;
	}
	
	

}
