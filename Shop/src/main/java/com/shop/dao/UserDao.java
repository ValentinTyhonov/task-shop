package com.shop.dao;

import java.util.List;

import com.shop.entity.Role;
import com.shop.entity.User;

public interface UserDao extends GeneralDao<User> {
	
	User getByEmail(String email);
	
	User getByUUID(String uuid);
	
	List<User> getWithRole(Role role);
	
	User getWithOrders(int id);

}
