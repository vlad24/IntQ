package com.appricots.intq.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.appricots.intq.NameOf;


@Entity
@Table(name=NameOf.TABLE_USESSION)
public class UserSession {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=NameOf.COLUMN_USESSION_ID)
	long id;
	
	@OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
	private User user;
	
	@Column(name=NameOf.COLUMN_USESSION_IDENTIIY_COOKIE)
	private String identCookie;
	
	
	@ManyToOne
	@JoinColumn(name = NameOf.COLUMN_QUESTION_ID)
	private Question lastQuestion;
	

	
	
	public UserSession() {
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Question getLastQuestion() {
		return lastQuestion;
	}

	public void setLastQuestion(Question lastQuestion) {
		this.lastQuestion = lastQuestion;
	}

}
