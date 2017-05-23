package com.appricots.intq.wrappers;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name=NameOf.TABLE_QUESTION)
public class QuestionSuggestion {

	@Override
	public String toString() {
		return "QuestionSuggestion [language=" + language + ", difficulty="
				+ difficulty + ", categories=" + categories + ", question="
				+ question + ", answer=" + answer + ", attachment="
				+ attachment + "]";
	}
	private Long language;
	private Long difficulty;
	private Set<Long> categories;
	private String question;
	private String answer;
	private String attachment;
	public Long getLanguage() {
		return language;
	}
	public void setLanguage(Long language) {
		this.language = language;
	}
	public Long getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Long difficulty) {
		this.difficulty = difficulty;
	}
	public Set<Long> getCategories() {
		return categories;
	}
	public void setCategories(Set<Long> categories) {
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
	
}
