package com.appricots.intq.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.dao.impl.UserSessionDAO;
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.wrappers.UserProfile;


@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	@Autowired
	UserSessionDAO userSessionDao;


	public UserService() {
	}

	public UserService(UserDAO userDao, UserSessionDAO userSessionDao) {
		super();
		this.userDao = userDao;
		this.userSessionDao= userSessionDao;
	}


	@Transactional
	public boolean validateIdentity(String identity) {
		return userSessionDao.getByIdentity(identity) != null;
	}
	
	@Transactional
	public User getUserForIdentity(String identity) {
		UserSession uSes = userSessionDao.getByIdentity(identity);
		if (uSes != null){
			return uSes.getUser();
		}else{
			return null;
		}
	}


	@Transactional
	public User getUserForCreds(UserCreds creds) {
		return userDao.getUserByCreds(creds);
	}

	
	@Transactional
	public UserSession getLastSessionByCookie(String identity) {
		return null;
	}

	@Transactional
	public Long registerUser(UserProfile profile) {
		try{
			User newUser = new User();
			newUser.setName(profile.getName());
			System.out.println("---------" + "trying to add" + newUser);
			newUser.setLastName(profile.getLastName());
			System.out.println("---------" + "trying to add" + newUser);
			newUser.setAge(profile.getAge());
			System.out.println("---------" + "trying to add" + newUser);
			newUser.setEmail(profile.getEmail());
			System.out.println("---------" + "trying to add" + newUser);
			newUser.setActiveness(0);
			System.out.println("---------" + "trying to add" + newUser);
			UserCreds newCreds = new UserCreds();
			System.out.println("---------" + "trying to add" + newUser);
			newCreds.setLogin(profile.getLogin());
			newCreds.setPassHash(profile.getPass());
			newUser.setCreds(newCreds);
			newCreds.setUser(newUser);
			System.out.println("---------" + "trying to add" + newUser);
			return userDao.create(newUser);
		}catch(ConstraintViolationException e){
			return null;
		}
	}

	@Transactional
	public void dropSession(String identity) {
		userSessionDao.deleteByIdentity(identity);
	}

	@Transactional
	public UserSession startNewSession(String login) {
		String cookie = generateCookie(login);
		UserSession newSession = new UserSession();
		newSession.setIdentCookie(cookie);
		User user = userDao.getByLogin(login);
		user.setSession(newSession);
		newSession.setUser(user);
		newSession.setLastQuestion(null);
		userSessionDao.create(newSession);
		return newSession;
	}

	private String generateCookie(String login) {
		return login + "_cookie";
	}

	@Transactional
	public void debugInit() {
		UserProfile user = new UserProfile();
		user.setAge(22);
		user.setEmail("email@domain.ru");
		user.setLogin("user");
		user.setPass("user");
		user.setName("Anonymous");
		System.out.println("+++++++++" + user);
		registerUser(user);
	}


}
