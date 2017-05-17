package com.appricots.intq.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.appricots.intq.NameOf;

@Entity
@Table(name = NameOf.TABLE_USER_CREDS)
public class UserCreds {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.COLUMN_USER_CREDS_ID)
	Long id;
	
	@Column(name = NameOf.COLUMN_USER_CREDS_LOGIN, nullable = false, unique=true)
	String login;

	@Column(name = NameOf.COLUMN_USER_CREDS_PASS, nullable = false)
	String passHash;
	
	@OneToOne(mappedBy = "creds", cascade = CascadeType.ALL)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "UserCreds [id=" + id + ", login=" + login + ", passHash="
				+ passHash + ", user=" + user + "]";
	}
	
	

	
}
