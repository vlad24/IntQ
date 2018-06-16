package com.appricots.intq.dao.impl;

import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCredentials;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAO extends DAO<User, Long>{

	public User getUserByCredentials(UserCredentials credentials) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append("SELECT  U FROM User U")
						.append(" JOIN   U.credentials UC ")
						.append(" WHERE  UC.login    = :login")
						.append(" AND    UC.passHash = :pass")
						.toString())
				.setParameter("login", credentials.getLogin())
				.setParameter("pass", credentials.getPassHash());
		User user = (User) selectQuery.uniqueResult();
		return user;
	}

	public Optional<User> getByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append("SELECT  U FROM User U")
						.append(" JOIN   U.credentials UC ")
						.append(" WHERE  U.credentials.login    = :login")
						.toString())
				.setParameter("login", login);
		User user = (User) selectQuery.uniqueResult();
		return Optional.ofNullable(user);
	}


}
