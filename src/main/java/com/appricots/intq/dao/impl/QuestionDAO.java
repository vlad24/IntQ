package com.appricots.intq.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appricots.intq.dao.AbstractQuestionDAO;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;

@Repository
public class QuestionDAO implements AbstractQuestionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long create(Question element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Question element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Question element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getNext(QuestionSelector selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getAll(int maxLimit) {
		// TODO Auto-generated method stub
		return null;
	}

}
