package com.appricots.intq.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionStatus;

@Repository
public class QuestionDAO extends DAO<Question, Long> {

	public Question getNextAccepted(QuestionSelector selector) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(selector);
		Query selectQuery = getQueryForConjSelector(selector, session);
		List<Question> list = selectQuery.list();
		if (!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	public List<Question> getAllNew() {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session.createQuery(
				new StringBuilder()
				.append(" SELECT Q FROM ").append(Question.class.getSimpleName()).append(" Q ")
				.append(" WHERE ")
				.append(" Q.status = :stat ")
				.toString()
				);
		selectQuery.setParameter("stat", QuestionStatus.NEW);
		List<Question> list = (List<Question>)selectQuery.list();
		return list;
	}

	private Query getQueryForDisjSelector(QuestionSelector selector, Session session) {
		Query selectQuery = session.createQuery(
				new StringBuilder()
				.append(" SELECT DISTINCT Q ").append(" FROM Question ").append(" Q ")
				.append(" JOIN  Q.categories ").append("QS")
				.append(" JOIN  Q.lang       ").append("QL")
				.append(" JOIN  Q.difficulty ").append("QD")
				.append(" WHERE ")
				.append(" QL.id = :lang ")   .append("AND")
				.append(" QD.id = :diff ")   .append("AND")
				.append(" Q.status = :stat ").append("AND")
				.append(" QS.id in (:categoryIds) ")
				.toString()
				);
		selectQuery.setParameterList("categoryIds", selector.getIds());
		selectQuery.setParameter("lang", selector.getLanguage());
		selectQuery.setParameter("diff", selector.getDifficulty());
		selectQuery.setParameter("stat", QuestionStatus.ACCEPTED);
		selectQuery.setFirstResult(selector.getShift());
		selectQuery.setMaxResults(1);
		return selectQuery;
	}
	
	public Query getQueryForConjSelector(QuestionSelector selector, Session session) {
		Query selectQuery = session.createQuery(
				new StringBuilder()
				.append(" SELECT DISTINCT Q ")
				.append(" FROM Question Q ")
				.append(" JOIN  Q.categories ").append("QS")
				.append(" JOIN  Q.lang       ").append("QL")
				.append(" JOIN  Q.difficulty ").append("QD")
				.append(" WHERE ")
				.append(" QD.id = :diff ")     .append("AND")
				.append(" QL.id = :lang ")     .append("AND")
				.append(" Q.status = :stat ")  .append("AND")
				.append(" :catN=").append("( ")
										.append(" SELECT count(DISTINCT QS2.id) FROM Question Q2")
										.append(" JOIN Q2.categories QS2")
										.append(" WHERE ")
										.append(" Q2.id = Q.id ").append("AND")
										.append(" QS2.id in (:categoryIds)")
								  .append(") ")
				.toString()
				);
		selectQuery.setParameterList("categoryIds", selector.getIds());
		selectQuery.setParameter("catN", (long)selector.getIds().size());
		selectQuery.setParameter("lang", selector.getLanguage());
		selectQuery.setParameter("diff", selector.getDifficulty());
		selectQuery.setParameter("stat", QuestionStatus.ACCEPTED);
		selectQuery.setFirstResult(selector.getShift());
		selectQuery.setMaxResults(1);
		return selectQuery;
	}
	



}
