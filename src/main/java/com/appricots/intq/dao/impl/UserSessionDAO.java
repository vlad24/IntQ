package com.appricots.intq.dao.impl;

import com.appricots.intq.model.UserSession;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserSessionDAO extends DAO<UserSession, Long>{

	public UserSession getByIdentity(String identity) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append(" SELECT  US FROM User U")
						.append(" JOIN    U.session US")
						.append(" WHERE  US.identCookie  = :cookie")
						.toString())
				.setParameter("cookie", identity);
		UserSession userSession = (UserSession) selectQuery.uniqueResult();
		System.out.println(userSession);
		return userSession;
	}
	
	public void deleteByIdentity(String identity) {
		delete(getByIdentity(identity));
	}


	public Optional<UserSession> getByUserId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session
				.createQuery(new StringBuilder()
						.append(" SELECT  US ")
						.append(" FROM    User U")
						.append(" JOIN    U.session US")
						.append(" WHERE   U.id  = :id")
						.toString())
				.setParameter("id", id);
		UserSession userSession = (UserSession) selectQuery.uniqueResult();
		return Optional.ofNullable(userSession);
	}
}
