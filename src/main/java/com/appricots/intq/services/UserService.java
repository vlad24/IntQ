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
	public void registerChecked(User user){
		userDao.create(user);
	}

	@Transactional
	public Long registerUser(UserProfile profile) {
		try{
			User newUser = new User();
			newUser.setName(profile.getName());
			newUser.setLastName(profile.getLastName());
			newUser.setAge(profile.getAge());
			newUser.setEmail(profile.getEmail());
			newUser.setActiveness(0);
			UserCreds newCreds = new UserCreds();
			newCreds.setLogin(profile.getLogin());
			newCreds.setPassHash(profile.getPass());
			newUser.setCreds(newCreds);
			return userDao.create(newUser);
		}catch(ConstraintViolationException e){
			return null;
		}
	}

	@Transactional
	public void dropSession(String identity) {
		userSessionDao.getByIdentity(identity);
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


}
