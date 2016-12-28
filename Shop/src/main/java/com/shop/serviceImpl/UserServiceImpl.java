package com.shop.serviceImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UserDao;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.service.UserService;
import com.shop.validator.Validator;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	public void save(User user, String confirmPass) throws Exception {
		
		validator.validate(user, confirmPass);
		
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public User getOne(int id) {
		return userDao.getOne(id);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Transactional
	public void changeRole(int id) {
		User user = userDao.getOne(id);
		Hibernate.initialize(user.getRole());
		
		if(user.getRole().equals(Role.ROLE_USER)) {
			user.setRole(Role.ROLE_ADMIN);
		} else {
			user.setRole(Role.ROLE_USER);
		}
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userDao.getByEmail(email);
	}

	public User getByUUID(String uuid) {
		return userDao.getByUUID(uuid);
	}

	public List<User> getWithRole(Role role) {
		return userDao.getWithRole(role);
	}

	@Transactional
	public void changeEnabled(int id) {
		User user = userDao.getOne(id);
		Hibernate.initialize(user.isEnabled());
		
		if(user.isEnabled()) {
			user.setEnabled(false);
		} else {
			user.setEnabled(true);
		}
	}

}
