package com.appricots.intq.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.appricots.intq.model.IntqUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.dao.impl.UserSessionDAO;
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.wrappers.UserProfile;


@Service
public class UserService implements UserDetailsService {

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
		newUser.setUsername(profile.getFirstName());
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


    public Optional<User> getCurrentUser() {
        IntqUserDetails principal = (IntqUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(principal.getIntqUser());
    }


    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByLogin(username);
		return new IntqUserDetails(user);
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


}
