package com.shop.service;

import java.util.List;

import com.shop.entity.Role;
import com.shop.entity.User;

public interface UserService {
	
	void save(User user, String confirmPass) throws Exception;

	List<User> getAll();

	User getOne(int id);

    void delete(int id);
    
    void update(User user);
    
    User getByEmail(String email);
    
    void changeRole(int id);
    
    void changeEnabled(int id);
    
    User getByUUID(String uuid);
    
    List<User> getWithRole(Role role);

}
