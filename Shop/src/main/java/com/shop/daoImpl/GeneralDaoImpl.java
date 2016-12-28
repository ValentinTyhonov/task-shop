package com.shop.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.GeneralDao;
import com.shop.entity.User;

public class GeneralDaoImpl<T> implements GeneralDao<T> {
	
	private Class<T> entity;
	
	public GeneralDaoImpl(Class<T> entity) { 
		this.entity = entity;
	}
	
	@PersistenceContext(unitName="Primary")
	private EntityManager entityManager;

	@Transactional
	public void save(T t) {
		entityManager.persist(t);
	}
	
	@Transactional
	public void saveAndFlush(T t) {
		entityManager.persist(t);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> getAll() {
		return entityManager.createQuery("from "+entity.getSimpleName()+" order by id desc").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public T getOne(int id) {
		return (T) entityManager.createQuery
				("select t from " + entity.getSimpleName() + " t where t.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Transactional
	public void delete(int id) {
		entityManager.remove(getOne(id));
	}

	@Transactional
	public void update(T t) {
		entityManager.merge(t);
	}

}
