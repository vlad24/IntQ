package com.appricots.intq.model;


public class QuestionSelector {
	private String category;
	private String difficulty;
	private String language;
	
	public QuestionSelector() {
		category   = "any";
		difficulty = "any";
		language   = "en";
	}
	
	@Override
	public String toString() {
		return "WordSelector [category=" + category + ", difficulty="
				+ difficulty + ", language=" + language + "]";
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
