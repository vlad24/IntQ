package com.appricots.intq.model;

import com.appricots.intq.NameOf;

import javax.persistence.*;

@Entity
@Table(name = NameOf.Table.CREDENTIALS)
public class UserCredentials {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = NameOf.Column.USER_CREDS_ID)
	Long id;
	
	@Column(name = NameOf.Column.USER_CREDS_LOGIN, nullable = false, unique=true)
	String login;

	@Column(name = NameOf.Column.USER_CREDS_PASS, nullable = false)
	String passHash;
	
	@OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
	private User user;


	public UserCredentials() {
	}


	public UserCredentials(String login, String passHash) {
		this.login = login;
		this.passHash = passHash;
	}


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
		return "UserCredentials [id=" + id + ", login=" + login + ", passHash="
				+ passHash + ", user=" + user + "]";
	}
	
	

	
}
