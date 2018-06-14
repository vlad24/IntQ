package com.appricots.intq.services;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.dao.impl.UserSessionDAO;
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.wrappers.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService{

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDAO userDao;

	@Autowired
	UserSessionDAO userSessionDao;


	public UserService() {
	}



	@Transactional
	public User getUserForCreds(UserCreds creds) {
		return userDao.getUserByCreds(creds);
	}



	@Transactional
	public Long registerUser(UserProfile profile) {
		User newUser = new User();
        newUser.setUsername(profile.getUsername());
		newUser.setFirstName(profile.getFirstName());
		newUser.setLastName(profile.getLastName());
		newUser.setAge(profile.getAge());
		newUser.setEmail(profile.getEmail());
		newUser.setActiveness(0);
		UserCreds newCreds = new UserCreds();
		newCreds.setLogin(profile.getLogin());
		newCreds.setPassHash(profile.getPass());
		newUser.setCreds(newCreds);
		newCreds.setUser(newUser);
		logger.info("Trying to add {}", newUser);
		return userDao.create(newUser);
	}

	@Transactional
	public void dropSession(String username) {
		userSessionDao.getByUserName(username).getUser().setSession(null);
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
		userProfile.setFirstName("Anonymous");
		registerUser(userProfile);
		return Arrays.asList(userProfile);
	}


    public Optional<User> getCurrentUser() {
        logger.info("@@@" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return Optional.empty();
//        IntqUserDetails userDetails = ((IntqUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        logger.warn("Principal: {}", userDetails);
//        return Optional.of(userDetails.getIntqUser());
    }


}
