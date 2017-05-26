package com.appricots.intq.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.NameOf;
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
		boolean validated = !identity.equals(NameOf.NOTHING) && (userSessionDao.getByIdentity(identity) != null);
		return validated;
	}

	@Transactional
	public User getUserForIdentity(String identity) {
		if (!identity.equals(NameOf.NOTHING)){
			UserSession uSes = userSessionDao.getByIdentity(identity);
			if (uSes != null){
				return uSes.getUser();
			}
		}
		return null;
	}


	@Transactional
	public User getUserForCreds(UserCreds creds) {
		return userDao.getUserByCreds(creds);
	}


	@Transactional
	public UserSession getCurrentSessionByCookie(String identity) {
		if (!identity.equals(NameOf.NOTHING)){
			return userSessionDao.getByIdentity(identity);
		}else{
			return null;
		}
	}

	@Transactional
	public Long registerUser(UserProfile profile) {
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
		newCreds.setUser(newUser);
		System.out.println("---------" + "trying to add" + newUser);
		return userDao.create(newUser);
	}

	@Transactional
	public void dropSession(String identity) {
		userSessionDao.getByIdentity(identity).getUser().setSession(null);
	}

	@Transactional
	public String startNewSession(String login) {
		String cookie = generateCookie(login);
		UserSession newSession = new UserSession();
		newSession.setIdentCookie(cookie);
		User user = userDao.getByLogin(login);
		user.setSession(newSession);
		newSession.setUser(user);
		userSessionDao.create(newSession);
		return cookie;
	}

	private String generateCookie(String login) {
		return login + "_cookie";
	}

	@Transactional
	public List<UserProfile> debugInit() {
		UserProfile userProfile = new UserProfile();
		userProfile.setAge(22);
		userProfile.setEmail("email@domain.ru");
		userProfile.setLogin("user");
		userProfile.setPass("user");
		userProfile.setName("Anonymous");
		registerUser(userProfile);
		return Arrays.asList(userProfile);
	}


}
