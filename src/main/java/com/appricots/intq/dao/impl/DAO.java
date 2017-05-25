package com.appricots.intq.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.appricots.intq.NameOf;

public abstract class DAO<T, ID extends Serializable> {
	
	protected Class<T> clazz;
	
	public DAO() {
		clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public ID create (T element){
		Session session = sessionFactory.getCurrentSession();
		System.out.println(element);
		return (ID) session.save(element);
	}
	
	public void delete(T element){
		Session session = sessionFactory.getCurrentSession();
		session.remove(element);
	}
	
	public void update(T element){
		Session session = sessionFactory.getCurrentSession();
		session.update(element);
		
	}

	public T get(ID id){
		Session session = sessionFactory.getCurrentSession();
		return session.find(clazz, id);
	}
	
	public List<T> getAll(Integer maxLimit) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session.createQuery("from " + clazz.getName());
		if (!maxLimit.equals(NameOf.MAX_POSSIBLE)){
			selectQuery.setMaxResults(maxLimit);
		}
		return (List<T>) selectQuery.list();
	}
	
}
