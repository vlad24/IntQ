package com.appricots.intq.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.appricots.intq.model.UserSession;

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
		return userSession;
	}
	
	public void deleteByIdentity(String identity) {
		delete(getByIdentity(identity));
	}


}
