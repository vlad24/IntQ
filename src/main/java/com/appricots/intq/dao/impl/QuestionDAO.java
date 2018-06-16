package com.appricots.intq.dao.impl;

import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionStatus;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class QuestionDAO extends DAO<Question, Long> {

	private final static Logger logger = LoggerFactory.getLogger(QuestionDAO.class);

	private static final String SELECT_ALL_NEW = new StringBuilder()
		.append(" SELECT Q FROM ").append(Question.class.getSimpleName()).append(" Q ")
		.append(" WHERE ")
		.append(" Q.status = :stat ")
		.toString();

	private static final String SELECT_NEXT_INTERSECTED = new StringBuilder()
		.append(" SELECT DISTINCT Q ")
		.append(" FROM Question Q ")
		.append(" JOIN  Q.categories ").append("QS")
		.append(" JOIN  Q.lang       ").append("QL")
		.append(" JOIN  Q.difficulty ").append("QD")
		.append(" WHERE ")
		.append(" QD.id = :diff ")     .append("AND")
		.append(" QL.id = :lang ")     .append("AND")
		.append(" Q.status = :stat ")  .append("AND")
		.append(" :numberOfCategories=")
			.append("( ")
				.append(" SELECT count(DISTINCT QS2.id) FROM Question Q2")
				.append(" JOIN Q2.categories QS2")
				.append(" WHERE ")
				.append(" Q2.id = Q.id ").append("AND")
				.append(" QS2.id in (:categoryIds)")
			.append(") ")
		.toString();
	
	private final static  String SELECT_NEXT_UNIONED = new StringBuilder()
		.append(" SELECT DISTINCT Q ").append(" FROM Question ").append(" Q ")
		.append(" JOIN  Q.categories ").append("QS")
		.append(" JOIN  Q.lang       ").append("QL")
		.append(" JOIN  Q.difficulty ").append("QD")
		.append(" WHERE ")
		.append(" QL.id = :lang ")   .append("AND")
		.append(" QD.id = :diff ")   .append("AND")
		.append(" Q.status = :stat ").append("AND")
		.append(" QS.id in (:categoryIds) ")
		.toString();
	
	
	public Question getNextAccepted(QuestionSelector selector) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Getting next accepted questions with selector {}", selector.toString());
		Query selectQuery;
		if (selector.isCatsUnioned()){
			selectQuery = session.createQuery(SELECT_NEXT_UNIONED);
		}else{
			selectQuery = session.createQuery(SELECT_NEXT_INTERSECTED);
			selectQuery.setParameter("numberOfCategories", (long)selector.getIds().size());
		}
		selectQuery.setParameterList("categoryIds", selector.getIds());
		selectQuery.setParameter("lang", selector.getLanguage());
		selectQuery.setParameter("diff", selector.getDifficulty());
		selectQuery.setParameter("stat", QuestionStatus.ACCEPTED);
		selectQuery.setFirstResult(selector.getShift());
		selectQuery.setMaxResults(1);
		List<Question> list = selectQuery.list();
		if (!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public List<Question> getAllNew() {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session.createQuery(SELECT_ALL_NEW);
		selectQuery.setParameter("stat", QuestionStatus.NEW);
		List<Question> list = (List<Question>)selectQuery.list();
		return list;
	}

}
