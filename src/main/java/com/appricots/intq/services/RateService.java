package com.appricots.intq.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.appricots.intq.dao.AbstractQuestionDAO;

public class RateService {

	@Autowired
	AbstractQuestionDAO questionDAO;
	
}
