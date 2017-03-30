package com.appricots.intq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	private String lang;
	private String question;
	private String answer;
	private String diffuculty;
	private long plusAmount;
	private long minusAmount;
	@Lob
	private byte[] attachment;


	public Question(){
		this.question   = "?";
		this.answer     = "!";
		this.lang       = "none";
		this.diffuculty = "none";
		this.attachment = null;
	}


	public Question(String question, String answer, String lang, String difficulty, byte[] attachment, long plusAmount, long minusAmount) {
		super();
		this.question = question;
		this.answer = answer;
		this.lang = lang;
		this.diffuculty = difficulty;
		this.attachment = attachment;
		this.plusAmount = 0;
		this.minusAmount = 0;
	}


	public String getAnswer() {
		return answer;
	}

	public byte[] getAttachment() {
		return attachment;
	}
	public String getDiffuculty() {
		return diffuculty;
	}
	public Long getId() {
		return id;
	}

	public String getLang() {
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


	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}


	public void setDiffuculty(String diffuculty) {
		this.diffuculty = diffuculty;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setLang(String lang) {
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


	@Override
	public String toString() {
		return "Question [id=" + id + ", lang=" + lang + ", question="
				+ question + ", answer=" + answer + ", diffuculty="
				+ diffuculty + ", plusAmount=" + plusAmount + ", minusAmount="
				+ minusAmount + ", attachment absent =" + (attachment == null)
				+ "]";
	}
}
