package com.appricots.intq.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.NameOf;
import com.appricots.intq.dao.impl.DifficultyDAO;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.wrappers.AliasedId;

@Service
public class DifficultyService {
	@Autowired
	DifficultyDAO difDao;

	public List<Difficulty> getAll() {
		return difDao.getAll(NameOf.MAX_POSSIBLE);
	}
	
	public List<AliasedId<Long>> getAliasedIds(){
		List<Difficulty> entities = getAll();
		List<AliasedId<Long>> aliasedIds = new ArrayList<AliasedId<Long>>(entities.size());
		for (Difficulty entity : entities) {
			aliasedIds.add(new AliasedId<Long>(entity.getId(), entity.getAlias()));
		}
		return aliasedIds;
	}

	public List<AliasedId<Long>> getAllForSuggestion() {
		//TODO try to move to super class
		List<Difficulty> entities = getAll();
		List<AliasedId<Long>> aliasedIds = new ArrayList<AliasedId<Long>>(entities.size());
		for (Difficulty entity : entities) {
			if (!entity.isMeta()){
				aliasedIds.add(new AliasedId<Long>(entity.getId(), entity.getAlias()));
			}
		}
		return aliasedIds;
	}
}
