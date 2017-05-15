package com.appricots.intq.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appricots.intq.dao.AbstractQuestionDAO;
import com.appricots.intq.model.Category;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
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
		return new Question("Python and multi-threading. Is it a good idea? List some ways to get some Python code to run in a parallel way.", 
				"Python doesn't allow multi-threading in the truest sense of the word. It has a multi-threading package but if you want to multi-thread to speed your code up, then it's usually not a good idea to use it. "
				+ "Python has a construct called the Global Interpreter Lock (GIL). The GIL makes sure that only one of your 'threads' "
				+ "can execute at any one time. A thread acquires the GIL, does a little work, then passes the GIL onto the next thread. This happens very quickly so to the human eye it may "
				+ "seem like your threads are executing in parallel, but they are really just taking turns using the same CPU core. All this GIL passing adds overhead to execution. "
				+ "This means that if you want to make your code run faster then using the threading package often isn't a good idea.", 
				new Lang("en"), new Difficulty("easy"), Collections.singleton(new Category()), "");
	}

	@Override
	public List<Question> getAll(int maxLimit) {
		// TODO Auto-generated method stub
		return null;
	}

}
