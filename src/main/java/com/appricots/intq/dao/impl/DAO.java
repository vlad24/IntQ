package com.appricots.intq.dao.impl;

import com.appricots.intq.NameOf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Abstract DAO extending which dao implementations are able to inherit CRUD operations
 * @param <T> type of entities this dao is manipulating (e.g. SomeEntity)
 * @param <ID> type of id entities are indexed with (e.g. Long)
 */
@SuppressWarnings("unchecked")
public abstract class DAO<T, ID extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

	private Class<T> clazz;
	
	DAO() {
		clazz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	

	public ID create (T element){
		Session session = sessionFactory.getCurrentSession();
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
