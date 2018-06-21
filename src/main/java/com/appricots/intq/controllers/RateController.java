package com.appricots.intq.controllers;

import com.appricots.intq.services.QuestionService;
import com.appricots.intq.wrappers.RateDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RateController {
	
	@Autowired
	QuestionService questionService;
	
	public RateController(QuestionService service) {
		questionService = service;
	}

	@Secured({"ROLE_ADMIN_SUCCESS", "ROLE_MODERATOR_ACCESS, ROLE_USER_RATE"})
	@ResponseBody
	@RequestMapping(value="rate.html", method = RequestMethod.POST)
	public String rate(
			@RequestParam("id")     long id,
			@RequestParam("delta")  String delta
			){
		return String.valueOf(questionService.rate(id, RateDirection.valueOf(delta).getNumericDelta()));
	}
}
