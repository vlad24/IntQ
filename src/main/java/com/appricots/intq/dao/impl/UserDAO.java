package com.appricots.intq.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCreds;

@Repository
public class UserDAO extends DAO<User, Long>{

	public User getUserByCreds(UserCreds creds) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append("SELECT  U FROM User U")
						.append(" JOIN   U.creds UC ")
						.append(" WHERE  UC.login    = :login")
						.append(" AND    UC.passHash = :pass")
						.toString())
				.setParameter("login", creds.getLogin())
				.setParameter("pass", creds.getPassHash());
		User user = (User) selectQuery.uniqueResult();
		return user;
	}

	public User getByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append("SELECT  U FROM User U")
						.append(" JOIN   U.creds UC ")
						.append(" WHERE  U.creds.login    = :login")
						.toString())
				.setParameter("login", login);
		User user = (User) selectQuery.uniqueResult();
		return user;
	}


}
