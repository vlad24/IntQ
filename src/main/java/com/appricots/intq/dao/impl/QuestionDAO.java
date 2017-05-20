package com.appricots.intq.dao.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Category;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionStatus;

@Repository
public class QuestionDAO extends DAO<Question, Long> {

	public Question getNextAccepted(QuestionSelector selector) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = null;
		boolean noCategoryBounds = (selector.getCategories().size() == 1 && selector.getCategories().contains(Category.ANY));
		final String langParam = "language";
		final String diffParam = "difficulty";
		final String statParam = "status";
		StringBuilder hqlQuery = new StringBuilder()
			.append(MessageFormat.format("SELECT QS FROM {} Q ", NameOf.TABLE_QUESTION))
			.append("JOIN  Q.categories QS ")
			.append(MessageFormat.format("AND {} = :{} ", NameOf.COLUMN_QUESTION_LANG,   langParam))
			.append(MessageFormat.format("AND {} = :{} ", NameOf.COLUMN_QUESTION_DIFF,   diffParam))
			.append(MessageFormat.format("AND {} = :{} ", NameOf.COLUMN_QUESTION_STATUS, statParam ));
		if (!noCategoryBounds){
			hqlQuery.append("WHERE QS.categories in :categories ");
		}
		selectQuery = session.createQuery(hqlQuery.toString());
		if (!noCategoryBounds){
			selectQuery.setParameter("categories", selector.getCategories());
		}
		selectQuery.setParameter(langParam, selector.getLanguage());
		selectQuery.setParameter(diffParam, selector.getDifficulty());
		selectQuery.setParameter(statParam, QuestionStatus.ACCEPTED.ordinal());
		selectQuery.setFirstResult(selector.getShift());
		selectQuery.setMaxResults(1);
		List<Question> list = selectQuery.list();
		return list.get(0);
	}

}
