package com.appricots.intq.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name = NameOf.TABLE_USER)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_USER_ID)
	Long id;

	@Column(name = NameOf.COLUMN_USER_LOGIN, nullable = false, unique=true)
	String login;

	@Column(name = NameOf.COLUMN_USER_PASS, nullable = false)
	String passHash;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=NameOf.COLUMN_USESSION_ID)
	UserSession session;
	
	public User() {
	}	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassHash() {
		return passHash;
	}
	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserSession getSession() {
		return session;
	}
	public void setSession(UserSession session) {
		this.session = session;
	}


}
