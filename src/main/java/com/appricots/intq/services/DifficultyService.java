package com.appricots.intq.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.NameOf;
import com.appricots.intq.dao.impl.DifficultyDAO;
import com.appricots.intq.model.Difficulty;

@Service
public class DifficultyService {
	@Autowired
	DifficultyDAO difDao;

	public List<Difficulty> getAllDiffs() {
		return difDao.getAll(NameOf.MAX_POSSIBLE);
	}
}
