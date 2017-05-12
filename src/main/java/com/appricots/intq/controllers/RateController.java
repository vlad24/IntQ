package com.appricots.intq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appricots.intq.services.QuestionService;

@Controller
public class RateController {
	
	@Autowired
	QuestionService questionService;
	
	public RateController(QuestionService service) {
		questionService = service;
	}
	
	@ResponseBody
	@RequestMapping(value="rate.html", method = RequestMethod.POST)
	public String rate(
			@RequestParam("id")     long id,
			@RequestParam("delta") String delta
			){
		return String.valueOf(questionService.rate(id, delta));
	}
}
