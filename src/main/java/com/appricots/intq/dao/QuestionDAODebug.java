package com.appricots.intq.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.appricots.intq.model.Question;
import com.appricots.intq.model.QuestionSelector;

public class QuestionDAODebug implements QuestionDAO{
	
	private Random random;
	private static List<Question> DEBIG_QUESTIONS = new ArrayList<Question>(
			Arrays.asList(
					new Question("What is Java?",    "PL!",             "en", "easy",   null, 0, 0),
					new Question("What is a loop?",  "construction",    "en", "middle", null, 0, 0),
					new Question("What is OS?",      "Hmmm...",         "en", "hard",   null, 0, 0)
			)
		);
	
	public QuestionDAODebug() {
		random = new Random();
		for (int i = 0; i < DEBIG_QUESTIONS.size(); i++){
			DEBIG_QUESTIONS.get(i).setId((long) i);
		}
	}
	
	
	@Override
	public Question getById(long id) {
		return DEBIG_QUESTIONS.get((int) (id % DEBIG_QUESTIONS.size()));
	}


	@Override
	public Question getNext(QuestionSelector selector) {
		return DEBIG_QUESTIONS.get(random.nextInt(DEBIG_QUESTIONS.size()));
	}


	@Override
	public double rate(long id, String action) {
		Question question = getById(id);
		if (action.equalsIgnoreCase(QuestionDAO.UP)) {
			System.out.println("increasing");
			long p = question.getPlusAmount();
			question.setPlusAmount(p + 1);
		}else if(action.equalsIgnoreCase(QuestionDAO.DOWN)){
			System.out.println("decreasing");
			long m = question.getMinusAmount();
			question.setMinusAmount(m + 1);
		}
		return question.calculateRating();
	}


	@Override
	public void addNew(Question newQuestion) {
		DEBIG_QUESTIONS.add(newQuestion);
	}
}
