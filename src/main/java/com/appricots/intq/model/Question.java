package com.appricots.intq.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name=NameOf.TABLE_QUESTION)
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=NameOf.COLUMN_QUESTION_ID)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = NameOf.COLUMN_LANG_ID)
	//@Column(name=NameOf.COLUMN_QUESTION_LANG, nullable=false)
	private Lang lang;
	
	@ManyToOne
	@JoinColumn(name = NameOf.COLUMN_DIFF_ID)
	//@Column(name=NameOf.COLUMN_QUESTION_DIFF, nullable=false)
	private Difficulty difficulty;
	
	@ManyToMany(cascade=CascadeType.ALL)  
	@JoinTable(name=NameOf.TABLE_QUESTION_CAT_REL, joinColumns=
		@JoinColumn(name=NameOf.COLUMN_QUESTION_ID), inverseJoinColumns=@JoinColumn(name=NameOf.COLUMN_CAT_ID)) 
	private Set<Category> categories;
	
	@Column(name=NameOf.COLUMN_QUESTION_CONTENT, nullable=false)
	private String question;
	
	@Column(name=NameOf.COLUMN_QUESTION_ANSWER, nullable=true)
	private String answer;
	
	@Column(name=NameOf.COLUMN_QUESTION_PLUS, nullable=false)
	private long plusAmount;
	
	@Column(name=NameOf.COLUMN_QUESTION_MINUS, nullable=false)
	private long minusAmount;
	
	@Column(name="attachmentURL", nullable=true)
	private String attachment;


	
	
	public Question(){
		this.question   = NameOf.NOTHING;
		this.answer     = NameOf.NOTHING;
		this.lang       = new Lang();
		this.difficulty = new Difficulty();
		this.attachment = NameOf.NOTHING;
	}


	public Question(String question, String answer, Lang lang, Difficulty difficulty, Set<Category> cats, String attachment) {
		super();
		this.question = question;
		this.answer = answer;
		this.lang = lang;
		this.difficulty = difficulty;
		this.attachment = attachment;
		this.categories = cats;
		this.plusAmount = 0;
		this.minusAmount = 0;
	}


	public String getAnswer() {
		return answer;
	}

	public String getAttachment() {
		return attachment;
	}
	public Difficulty getDiffuculty() {
		return difficulty;
	}
	public Long getId() {
		return id;
	}

	public Lang getLang() {
		return lang;
	}
	
	
	public long getMinusAmount() {
		return minusAmount;
	}
	
	public long getPlusAmount() {
		return plusAmount;
	}

	public String getQuestion() {
		return question;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}


	public void setDiffuculty(Difficulty diffuculty) {
		this.difficulty = diffuculty;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setLang(Lang lang) {
		this.lang = lang;
	}


	public void setMinusAmount(long minusAmount) {
		this.minusAmount = minusAmount;
	}


	public void setPlusAmount(long plusAmount) {
		this.plusAmount = plusAmount;
	}


	public void setQuestion(String question) {
		this.question = question;
	}
	
	public int calculateRating(){
		double result = 0;
		if (plusAmount + minusAmount != 0){
			result = ((double) plusAmount) / (plusAmount + minusAmount);
		}
		return (int) (100 * result);
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", lang=" + lang + ", question="
				+ question + ", answer=" + answer + ", diffuculty="
				+ difficulty + ", plusAmount=" + plusAmount + ", minusAmount="
				+ minusAmount + ", attachment absent =" + (attachment == null)
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result
				+ ((categories == null) ? 0 : categories.hashCode());
		result = prime * result
				+ ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + (int) (minusAmount ^ (minusAmount >>> 32));
		result = prime * result + (int) (plusAmount ^ (plusAmount >>> 32));
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (minusAmount != other.minusAmount)
			return false;
		if (plusAmount != other.plusAmount)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}


}
