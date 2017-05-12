package com.appricots.intq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.dao.AbstractQuestionDAO;
import com.appricots.intq.model.Question;

@Service
public class QuestionService {

	@Autowired
	AbstractQuestionDAO qDao;
	
	public QuestionService(AbstractQuestionDAO dao) {
		qDao = dao;
	}
	
	public void addNew(Question q) {
		// TODO Auto-generated method stub
		
	}

	public double rate(long id, String delta) {
		// TODO Auto-generated method stub
		return 0;
	}

}
