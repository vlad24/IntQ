package com.appricots.intq.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.appricots.intq.dao.impl.QuestionDAO;

public class RateService {

	@Autowired
	QuestionDAO questionDAO;
	
}
