package com.appricots.intq.dao.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.wrappers.AliasedId;

@Repository
public class DifficultyDAO extends DAO<Difficulty, Long>{

	@Transactional
	public List<Difficulty> getAll(Integer maxLimit) {
		return super.getAll(NameOf.MAX_POSSIBLE);
	}
	
	@Transactional
	public Difficulty findByAlias(String alias){
		Session session = sessionFactory.getCurrentSession();
		String aliasParam = "alias";
		Query selectQuery = session.createQuery(MessageFormat.format("SELECT D from {} where D.{} = {}", 
				NameOf.TABLE_DIFFICULTY, NameOf.COLUMN_DIFF_ALIAS, aliasParam)
		);
		selectQuery.setParameter(aliasParam, alias);
		return (Difficulty) selectQuery.uniqueResult();
	}

	public List<Difficulty> getAllNonMeta(Integer maxLimit) {
		Session session = sessionFactory.getCurrentSession();
		Query selectQuery = session.createQuery("SELECT D from Difficulty D where D.isMeta is false");
		if (!maxLimit.equals(NameOf.MAX_POSSIBLE)){
			selectQuery.setMaxResults(maxLimit);
		}
		return (List<Difficulty>)selectQuery.list();
	}

}
