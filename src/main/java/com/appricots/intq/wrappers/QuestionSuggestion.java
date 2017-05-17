package com.appricots.intq.wrappers;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name=NameOf.TABLE_QUESTION)
public class QuestionSuggestion {

	private String language;
	private String difficulty;
	private Set<String> categories;
	private String question;
	private String answer;
	private String attachment;
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public void setCategories(Set<String> categories) {
		this.categories = categories;
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
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public QuestionSuggestion() {
		// TODO Auto-generated constructor stub
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
