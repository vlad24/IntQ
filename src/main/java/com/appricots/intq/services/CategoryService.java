package com.appricots.intq.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.NameOf;
import com.appricots.intq.dao.DAO;
import com.appricots.intq.model.Category;

@Service
public class CategoryService {
	@Autowired
	DAO<Category, Long> categoryDAO;
	
	public List<Category> getAllCategories() {
		return categoryDAO.getAll(NameOf.MAX_POSSIBLE);
	}
	
}
