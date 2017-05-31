package com.appricots.intq.wrappers.respobjects;

import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class QuestionResponse {
	private long id;
	private String question;
	private String answer;
	private String attachement;
	private int rating;
	private QuestionSelector selector;
	
	public static QuestionResponse fromQuestionAndSelector(Question nextQuestion, QuestionSelector selector){
		return new QuestionResponse(
				nextQuestion.getId(),
		        nextQuestion.getQuestion(),
		        nextQuestion.getAnswer(),
		        nextQuestion.getAttachment(),
		        nextQuestion.calculateRating(),
		        selector);
	}
	
	public QuestionResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public QuestionResponse(long id, String question, String answer,
			String attachement, int rating, QuestionSelector selector) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.attachement = attachement;
		this.rating = rating;
		this.selector = selector;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAttachement() {
		return attachement;
	}
	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public QuestionSelector getSelector() {
		return selector;
	}
	public void setSelector(QuestionSelector selector) {
		this.selector = selector;
	}
}