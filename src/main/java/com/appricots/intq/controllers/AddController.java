package com.appricots.intq.controllers;

import java.util.Collections;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.appricots.intq.model.Category;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
import com.appricots.intq.model.Question;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.wrappers.QuestionSuggestion;

@Controller
public class AddController {

	@Autowired
	QuestionService questionService;
	@Autowired
	ReCaptchaImpl reCaptcha;

	public AddController(QuestionService dao) {
		questionService = dao;
	}

	@RequestMapping(value="add.html", method = RequestMethod.GET)
	public String add(Model model){
		//TODO 
		model.addAttribute("question", new QuestionSuggestion());
		return "add";		
	}

	@RequestMapping(value="add.html",method = RequestMethod.POST)
	public String addToBase(
			@RequestParam("question")   String question,
			@RequestParam("answer")     String answer,
			@RequestParam("difficulty")	String difficulty,
			@RequestParam("category")   String category,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			ServletRequest servletRequest
			){
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			Question q = new Question(
					question, 
					answer,
					new Lang(LocaleContextHolder.getLocale().toLanguageTag()),
					new Difficulty(difficulty),
					Collections.singleton(new Category()),
					null
			);
			System.out.println("Got new question:" + q);
			questionService.addNew(q);
			//TODO process category!
			return "added";
		}else{
			return "error";
		}
	}
}
