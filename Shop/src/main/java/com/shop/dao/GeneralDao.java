package com.shop.dao;

import java.util.List;

public interface GeneralDao<T> {
	
	void save(T t);
	
	void saveAndFlush(T t);

	List<T> getAll();

    T getOne(int id);

    void delete(int id);
    
    void update(T t);

}
