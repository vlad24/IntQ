package com.appricots.intq.services;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.dao.impl.UserSessionDAO;
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCredentials;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.wrappers.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDAO userDao;

	@Autowired
	UserSessionDAO userSessionDao;


	public UserService() {
	}



	@Transactional
	public User getUserByCredentials(UserCredentials credentials) {
		return userDao.getUserByCredentials(credentials);
	}



	@Transactional
	public Long registerUser(UserProfile profile) {
		User newUser = new User();
		newUser.setFirstName(profile.getFirstName());
		newUser.setLastName(profile.getLastName());
		newUser.setAge(profile.getAge());
		newUser.setEmail(profile.getEmail());
		newUser.setActiveness(0);
		UserCredentials newCreds = new UserCredentials();
		newCreds.setLogin(profile.getLogin());
		newCreds.setPassHash(profile.getPass());
		newUser.setCredentials(newCreds);
		newCreds.setUser(newUser);
		logger.info("Trying to add {}", newUser);
		return userDao.create(newUser);
	}

	@Transactional
	public void dropSession(Long userId) {
        Optional<UserSession> userSessionOptional = userSessionDao.getByUserId(userId);
        if (userSessionOptional.isPresent()) {
            UserSession userSession = userSessionOptional.get();
            userSession.getUser().setSession(null);
            userDao.update(userSession.getUser());
        }
	}

	@Transactional
	public String startNewSession(String login) {
		String cookie = generateCookie(login);
		UserSession newSession = new UserSession();
		newSession.setIdentCookie(cookie);
		User user = userDao.getByLogin(login).orElseThrow(() -> new IllegalArgumentException("User not found. Cannot start session"));
		user.setSession(newSession);
		newSession.setUser(user);
		userSessionDao.create(newSession);
		return cookie;
	}



	private String generateCookie(String login) {
		return login + "_cookie";
	}


}
