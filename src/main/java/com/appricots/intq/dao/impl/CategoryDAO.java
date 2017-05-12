package com.appricots.intq.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.appricots.intq.dao.DAO;
import com.appricots.intq.model.Category;

@Repository
public class CategoryDAO implements DAO<Category, Long>{

	@Override
	public Long create(Category element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Category element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll(int maxLimit) {
		// TODO Auto-generated method stub
		return Arrays.asList(new Category(0, "Java", null), new Category(0, "Python", null));
	}

}
