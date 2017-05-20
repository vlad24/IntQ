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
	@JoinColumn(name = NameOf.COLUMN_QUESTION_ID, nullable=true)
	private Question lastQuestion;
	
	
	public UserSession(long id, User user, String identCookie,
			Question lastQuestion) {
		super();
		this.id = id;
		this.user = user;
		this.identCookie = identCookie;
		this.lastQuestion = lastQuestion;
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentCookie() {
		return identCookie;
	}

	public void setIdentCookie(String identCookie) {
		this.identCookie = identCookie;
	}

	@Override
	public String toString() {
		return "UserSession [id=" + id + ", user=" + user + ", identCookie="
				+ identCookie + ", lastQuestion=" + lastQuestion + "]";
	}

}
