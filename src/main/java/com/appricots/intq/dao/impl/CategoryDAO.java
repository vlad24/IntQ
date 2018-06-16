package com.appricots.intq.dao.impl;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Repository
public class CategoryDAO extends DAO<Category, Long>{

	@Transactional
	public List<Category> getAll(Integer maxLimit) {
		return super.getAll(NameOf.MAX_POSSIBLE);
	}
	
	
	@Transactional
	public Category findByAlias(String alias){
		Session session = sessionFactory.getCurrentSession();
		String aliasParam = "alias";
		Query selectQuery = session.createQuery(MessageFormat.format("SELECT C from {} where C.{} = {}", 
				NameOf.Table.CATEGORY, NameOf.Column.CATEGORY_ALIAS, aliasParam)
		);
		selectQuery.setParameter(aliasParam, alias);
		return (Category) selectQuery.uniqueResult();
	}

}
