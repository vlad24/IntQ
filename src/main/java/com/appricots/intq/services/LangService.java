package com.appricots.intq.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.NameOf;
import com.appricots.intq.dao.impl.LangDAO;
import com.appricots.intq.model.Lang;
import com.appricots.intq.wrappers.AliasedId;

@Service
public class LangService {
	@Autowired
	LangDAO langDAO;

	public List<Lang> getAll() {
		return langDAO.getAll(NameOf.MAX_POSSIBLE);
	}
	
	public List<AliasedId<Long>> getAliasedIds(){
		List<Lang> entities = getAll();
		List<AliasedId<Long>> aliasedIds = new ArrayList<AliasedId<Long>>(entities.size());
		for (Lang entity : entities) {
			aliasedIds.add(new AliasedId<Long>(entity.getId(), entity.getAlias()));
		}
		return aliasedIds;
	}

}
