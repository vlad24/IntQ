package com.appricots.intq.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionStatus;

@Repository
public class QuestionDAO extends DAO<Question, Long> {

	public Question getNextAccepted(QuestionSelector selector) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session.createQuery(
				new StringBuilder()
				.append(" SELECT Q FROM ").append(Question.class.getSimpleName()).append(" Q ")
				.append(" JOIN  Q.categories ").append("QS")
				.append(" JOIN  Q.lang       ").append("QL")
				.append(" JOIN  Q.difficulty ").append("QD")
				.append(" WHERE ")
				.append(" QL.id = :lang ")   .append("AND")
				.append(" QD.id = :diff ")   .append("AND")
				.append(" Q.status = :stat ").append("AND")
				//.append(" Q.isMeta = :meta ").append("AND")
				.append(" QS.id in (:categoryIds) ")
				.toString()
				);
		selectQuery.setParameterList("categoryIds", selector.getIds());
		selectQuery.setParameter("lang", selector.getLanguage());
		selectQuery.setParameter("diff", selector.getDifficulty());
		selectQuery.setParameter("stat", QuestionStatus.ACCEPTED);
		//selectQuery.setParameter("meta", false);
		selectQuery.setFirstResult(selector.getShift());
		selectQuery.setMaxResults(1);
		List<Question> list = selectQuery.list();
		if (!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}


}
