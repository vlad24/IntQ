package com.appricots.intq.dao;

import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;

public interface AbstractQuestionDAO extends DAO<Question, Long>  {
	public Question getNext(QuestionSelector selector);
}
