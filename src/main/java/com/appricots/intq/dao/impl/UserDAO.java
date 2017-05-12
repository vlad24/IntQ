package com.appricots.intq.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appricots.intq.dao.DAO;
import com.appricots.intq.model.User;

public class UserDAO implements DAO<User, Long>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(User element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll(int maxLimit) {
		// TODO Auto-generated method stub
		return null;
	}


}
