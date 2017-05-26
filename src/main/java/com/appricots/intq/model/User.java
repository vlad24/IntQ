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

	@Column(name = NameOf.COLUMN_USER_NAME)
	String name;
	@Column(name = NameOf.COLUMN_USER_LAST_NAME)
	String lastName;
	@Column(name = NameOf.COLUMN_USER_EMAIL)
	String email;
	@Column(name = NameOf.COLUMN_USER_AGE)
	int age;
	@Column(name = NameOf.COLUMN_USER_ACTIVENESS)
	long activeness;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name=NameOf.COLUMN_USESSION_ID)
	UserSession session;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=NameOf.COLUMN_USER_CREDS_ID)
	UserCreds creds;
	
	public User() {
	}	
	
	public User(Long id, String name, String lastName, String email, int age,
			long activeness, UserSession session, UserCreds creds) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.activeness = activeness;
		this.session = session;
		this.creds = creds;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public UserCreds getCreds() {
		return creds;
	}

	public void setCreds(UserCreds creds) {
		this.creds = creds;
	}

	public long getActiveness() {
		return activeness;
	}

	public void setActiveness(long activeness) {
		this.activeness = activeness;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName
				+ ", email=" + email + ", age=" + age + ", activeness="
				+ activeness + ", session=" + session + "]";
	}





}
