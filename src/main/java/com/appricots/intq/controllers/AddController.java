package com.appricots.intq.controllers;

import java.util.Collections;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Category;
import com.appricots.intq.model.Difficulty;
import com.appricots.intq.model.Lang;
import com.appricots.intq.model.Question;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.wrappers.QuestionSuggestion;

@Controller
public class AddController {

	@Autowired
	QuestionService questionService;
	@Autowired
	CategoryService categoryService;
//	@Autowired
//	DifficultyService difService;
//	@Autowired
//	LanguageService langService;
	@Autowired
	ReCaptchaImpl reCaptcha;

	public AddController(QuestionService qService, CategoryService catService) {
		categoryService = catService;
		questionService = qService;
	}

	@RequestMapping(value="add.html", method = RequestMethod.GET)
	public String add(Model model){
		//TODO 
		model.addAttribute("questionSuggestion", new QuestionSuggestion());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "add";		
	}

	@RequestMapping(value="add.html",method = RequestMethod.POST)
	public String addToBase(
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			@ModelAttribute("qusestionSuggestion") QuestionSuggestion suggestion,
			ServletRequest servletRequest,
			Model model
			){
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			Question q = new Question(
					suggestion.getQuestion(), 
					suggestion.getAnswer(),
					new Lang(LocaleContextHolder.getLocale().toLanguageTag()),
					new Difficulty("en"),
					Collections.singleton(new Category()),
					null
			);
			System.out.println("Got new question:" + q);
			questionService.addNew(q);
			model.addAttribute(NameOf.SUCCESS_MSG, "Question is added and will soon be moderated.");
		}else{
			model.addAttribute(NameOf.ERROR_MSG, "wrong captcha");
		}
		model.addAttribute("questionSuggestion", suggestion);
		model.addAttribute("categories", categoryService.getAllCategories());
		return "add";
	}
}
