package com.appricots.intq.dao.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Lang;

@Repository
public class LangDAO extends DAO<Lang, Long>{

	@Transactional
	public List<Lang> getAll(Integer maxLimit) {
		return super.getAll(NameOf.MAX_POSSIBLE);
	}
	
	@Transactional
	public Lang findByAlias(String alias){
		Session session = sessionFactory.getCurrentSession();
		String aliasParam = "alias";
		Query selectQuery = session.createQuery(MessageFormat.format("SELECT L from {} where L.{} = {}", 
				NameOf.Table.LANGUAGE, NameOf.Column.LANGUAGE_ALIAS, aliasParam)
		);
		selectQuery.setParameter(aliasParam, alias);
		return (Lang) selectQuery.uniqueResult();
	}

}
