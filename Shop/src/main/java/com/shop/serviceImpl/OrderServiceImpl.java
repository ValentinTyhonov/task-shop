package com.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.service.OrderService;
import com.shop.validator.Validator;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

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
	
	

}
