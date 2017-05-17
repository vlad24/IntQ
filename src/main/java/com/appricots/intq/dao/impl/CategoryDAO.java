package com.appricots.intq.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.appricots.intq.model.Category;

@Repository
public class CategoryDAO extends DAO<Category, Long>{

	public List<Category> getAll(Integer maxLimit) {
		return Arrays.asList(new Category(0, "Java", null), new Category(0, "Python", null));
	}

}
