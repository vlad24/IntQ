package com.appricots.intq.wrappers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.appricots.intq.model.Category;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
import com.appricots.intq.model.Question;


public class QuestionSelector {
	private Set<String> categories;
	private String difficulty;
	private String language;
	
	public static QuestionSelector toSelector(Question q){
		Set<String> catAliases = new HashSet<String>();
		for (Iterator<Category> iterator = q.getCategories().iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			catAliases.add(category.getAlias());
		}
		return new QuestionSelector(catAliases, q.getDiffuculty().getAlias(), q.getLang().getAlias());
	}
	
	public QuestionSelector(Set<String> categories, String difficulty,
			String language) {
		super();
		this.categories = categories;
		this.difficulty = difficulty;
		this.language = language;
	}

	public QuestionSelector() {
		categories   = Collections.singleton(Category.ANY);
		difficulty = Difficulty.ANY;
		language   = Lang.ANY;
	}
	
	@Override
	public String toString() {
		return "QSelector [category=" + categories + ", difficulty="
				+ difficulty + ", language=" + language + "]";
	}
	
	public Set<String> getCategory() {
		return categories;
	}
	public void setCategory(Set<String> category) {
		this.categories = category;
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
