package com.appricots.intq.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appricots.intq.NameOf;
import com.appricots.intq.dao.impl.LangDAO;
import com.appricots.intq.model.Lang;

@Service
public class LangService {
	@Autowired
	LangDAO langDAO;

	public List<Lang> getAllLangs() {
		return langDAO.getAll(NameOf.MAX_POSSIBLE);
	}
}
