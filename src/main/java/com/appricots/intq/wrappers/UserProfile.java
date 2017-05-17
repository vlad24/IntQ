package com.appricots.intq.wrappers;

import com.appricots.intq.model.User;


/**
 * @author vlad
 *
 */
public class UserProfile {
	public UserProfile() {
	}
	
	public UserProfile(String login, String pass, String name,
			String lastName, int age, String email) {
		super();
		this.login = login;
		this.pass = pass;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}

	String login;
	String pass;
	String name;
	String lastName;
	String email;
	int age;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserProfile [login=" + login + ", pass=" + pass + ", name="
				+ name + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + "]";
	}


	

	
}
