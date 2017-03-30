package com.appricots.intq.dao;

import com.appricots.intq.model.Question;
import com.appricots.intq.model.QuestionSelector;

public interface QuestionDAO {
	public static final String UP   = "up";
	public static final String DOWN = "down";
	public Question getById(long id);
	public Question getNext(QuestionSelector selector);
	public double rate(long id, String action);
	public void addNew(Question newQuestion);
	
}
