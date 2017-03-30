package com.appricots.intq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.appricots.intq.dao.QuestionDAO;
import com.appricots.intq.model.Question;
import com.appricots.intq.model.QuestionSelector;

@Controller
public class MainController {

	@Autowired
	private QuestionDAO questionDAO;
	
	public MainController(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String getNext(
			@ModelAttribute("questionSelector") QuestionSelector selector,
			BindingResult result,
			Model model){
		if (result.hasErrors()){
			return "error";
		}
		selector.setLanguage(LocaleContextHolder.getLocale().toLanguageTag());
		System.out.println("Got selector:" + selector.toString());
		Question nextQuestion = questionDAO.getNext(selector);
		System.out.println("Next question:" + nextQuestion.toString());
		model.addAttribute("id",           nextQuestion.getId());
        model.addAttribute("question",     nextQuestion.getQuestion());
        model.addAttribute("answer",       nextQuestion.getAnswer());
        model.addAttribute("attachment",   nextQuestion.getAttachment());
        model.addAttribute("rating",       nextQuestion.calculateRating());
		return "main";
	}


	
}
