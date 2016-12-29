package com.shop.dao;

import java.util.List;

import com.shop.entity.Order;
import com.shop.entity.User;

public interface OrderDao extends GeneralDao<Order> {
	
	Order getNotPaidByUser(User user);
	
	List<Order> getByUser(User user);

}
