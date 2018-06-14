package com.appricots.intq.model;

import com.appricots.intq.NameOf;
import com.appricots.intq.wrappers.QuestionStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name=NameOf.Table.QUESTION)
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=NameOf.Column.QUESTION_ID)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = NameOf.Column.LANGUAGE_ID)
	private Lang lang;
	
	@ManyToOne
	@JoinColumn(name = NameOf.Column.DIFFICULTY_ID)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name=NameOf.Table.LINK_QUESTION_CATEGORY, joinColumns=
		@JoinColumn(name=NameOf.Column.QUESTION_ID), inverseJoinColumns=@JoinColumn(name=NameOf.Column.CATEGORY_ID))
	private Set<Category> categories;
	
	@Column(name=NameOf.Column.QUESTION_CONTENT, nullable=false, length=1024)
	private String question;
	
	@Column(name=NameOf.Column.QUESTION_ANSWER, nullable=true, length=2048)
	private String answer;
	
	@Column(name=NameOf.Column.QUESTION_PLUS, nullable=false)
	private long plusAmount;
	
	@Column(name=NameOf.Column.QUESTION_MINUS, nullable=false)
	private long minusAmount;
	
	@Column(name=NameOf.Column.QUESTION_ATTACH_URL, nullable=true)
	private String attachment;
	   
	@Enumerated(EnumType.STRING)
	@Column(name=NameOf.Column.QUESTION_STATUS, nullable=false)
	private QuestionStatus status;

	
	public Question(){
	}


	public Question(String question, String answer, Lang lang, Difficulty difficulty, Set<Category> cats, String attachment, QuestionStatus status) {
		super();
		this.question = question;
		this.answer = answer;
		this.lang = lang;
		this.difficulty = difficulty;
		this.attachment = attachment;
		this.categories = cats;
		this.plusAmount = 0;
		this.minusAmount = 0;
		this.status = status;
	}


	public String getAnswer() {
		return answer;
	}

	public String getAttachment() {
		return attachment;
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
		return "Question [id=" + id + "]";
	}


	public Difficulty getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}


	public QuestionStatus getStatus() {
		return status;
	}


	public void setStatus(QuestionStatus status) {
		this.status = status;
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
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		return status == other.status;
	}


}
