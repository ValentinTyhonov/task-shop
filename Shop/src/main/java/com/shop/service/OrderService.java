package com.shop.service;

import java.util.List;

import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.User;

public interface OrderService {
	
	void save(Order order);

	List<Order> getAll();

	Order getOne(int id);

    void delete(int id);
    
    void update(Order order);
    
    Order getNotPaidByUser(User user);
    
    void placeOrder(Order order, int total);
    
    int totalPrice(List<Product> products);
    
    void removeFromCart(int userId, int productId);
    
    String listOfBoughtProducts(Order order);

}
