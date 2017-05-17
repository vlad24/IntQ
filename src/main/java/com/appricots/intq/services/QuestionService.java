package com.appricots.intq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.dao.impl.QuestionDAO;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;

@Service
public class QuestionService {

	@Autowired
	QuestionDAO qDao;
	
	public QuestionService(QuestionDAO dao) {
		qDao = dao;
	}
	
	
	
	public void addNew(Question q) {
		System.out.println("adding " + q);
		
	}

	public double rate(long id, String delta) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Question getNext(QuestionSelector selector) {
		return qDao.getNext(selector);
	}

}
