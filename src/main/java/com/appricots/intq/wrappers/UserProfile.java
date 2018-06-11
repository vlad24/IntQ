package com.appricots.intq.wrappers;


/**
 * @author vlad
 *
 */
public class UserProfile {
    public UserProfile() {
	}


	public UserProfile(String login, String pass, String username, String name,
			String lastName, int age, String email) {
		super();
		this.login = login;
		this.pass = pass;
		this.username = username;
		this.firstName = name;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}


    String login;
    String pass;
    String username;
    String firstName;
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


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
