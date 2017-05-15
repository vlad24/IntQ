package com.appricots.intq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appricots.intq.dao.AbstractQuestionDAO;
import com.appricots.intq.model.Question;
import com.appricots.intq.wrappers.QuestionSelector;

@Controller
public class QuestionController {

	@Autowired
	private AbstractQuestionDAO questionDAO;
	
	public QuestionController(AbstractQuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	@RequestMapping(value="/q", method=RequestMethod.GET)
	public String getNext(
			@ModelAttribute("questionSelector") QuestionSelector selector,
			Model model){
		selector.setLanguage(LocaleContextHolder.getLocale().toLanguageTag());
		System.out.println("Got selector:" + selector.toString());
		Question nextQuestion = questionDAO.getNext(selector);
		System.out.println("Next question:" + nextQuestion.toString());
		model.addAttribute("id",           nextQuestion.getId());
        model.addAttribute("question",     nextQuestion.getQuestion());
        model.addAttribute("answer",       nextQuestion.getAnswer());
        model.addAttribute("attachment",   nextQuestion.getAttachment());
        model.addAttribute("rating",       nextQuestion.calculateRating());
		return "question";
	}
	
	
}
