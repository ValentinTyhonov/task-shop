package com.shop.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shop.dao.OrderDao;
import com.shop.entity.Order;
import com.shop.entity.User;

@Repository
public class OrderDaoImpl extends GeneralDaoImpl<Order> implements OrderDao {
	
	public OrderDaoImpl() {
		super(Order.class);
	}

	@PersistenceContext(unitName="Primary")
	private EntityManager entityManager;

	public Order getNotPaidByUser(User user) {
		return (Order) entityManager.createQuery
				("select o from orders o where o.user = :user AND o.paid = false")
				.setParameter("user", user).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByUser(User user) {
		return (List<Order>) entityManager.createQuery
				("select o from orders o where o.user = :user")
				.setParameter("user", user).getResultList();
	}

}
