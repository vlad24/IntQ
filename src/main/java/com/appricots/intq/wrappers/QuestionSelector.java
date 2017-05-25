package com.appricots.intq.wrappers;

import java.util.List;


public class QuestionSelector {
	private List<Long> ids;
	private Long difficulty;
	private Long language;
	private int shift;
	
//	public static QuestionSelector toSelector(Question q){
//		Set<Long> catIds = new HashSet<Long>();
//		for (Iterator<Category> iterator = q.getCategories().iterator(); iterator.hasNext();) {
//			Category category = (Category) iterator.next();
//			catIds.add(category.getId());
//		}
//		return new QuestionSelector(new ArrayList<Long>(catIds),
//				new AliasedId<Long>(q.getDifficulty().getId(), q.getDifficulty().getAlias()),
//				new AliasedId<Long>(q.getLang().getId(), q.getLang().getAlias())
//			);
//	}
	
	public QuestionSelector(List<Long> ids, Long dif, Long lang) {
		super();
		this.ids = ids;
		this.difficulty = dif;
		this.language = lang;
		this.shift = 0;
	}

	public QuestionSelector() {
	}
	

	@Override
	public String toString() {
		return "QuestionSelector [ids=" + ids + ", difficulty=" + difficulty
				+ ", language=" + language + ", shift=" + shift + "]";
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}



	public Long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Long difficulty) {
		this.difficulty = difficulty;
	}

	public Long getLanguage() {
		return language;
	}

	public void setLanguage(Long language) {
		this.language = language;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}
	

	
}
