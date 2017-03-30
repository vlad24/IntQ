package com.appricots.intq.controllers;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.appricots.intq.dao.QuestionDAO;
import com.appricots.intq.model.Question;

@Controller
public class AddController {

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	ReCaptchaImpl reCaptcha;

	public AddController(QuestionDAO dao) {
		questionDAO = dao;
	}

	@RequestMapping(value="add.html", method = RequestMethod.GET)
	public String add(){
		return "add";		
	}

	@RequestMapping(value="add.html",method = RequestMethod.POST)
	public String addToBase(
			@RequestParam("question")   String question,
			@RequestParam("answer")     String delta,
			@RequestParam("difficulty")	String difficulty,
			@RequestParam("category")   String category,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			ServletRequest servletRequest
			){
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			Question q = new Question(question,delta, LocaleContextHolder.getLocale().toLanguageTag(), difficulty, null, 0, 0);
			System.out.println("Got new question:" + q);
			questionDAO.addNew(q);
			//process category!
			return "added";
		}else{
			return "error";
		}
	}
}
