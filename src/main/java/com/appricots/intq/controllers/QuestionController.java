package com.appricots.intq.controllers;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Question;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.DifficultyService;
import com.appricots.intq.services.LangService;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.QuestionSelector;
import com.appricots.intq.wrappers.QuestionSuggestion;

@Controller
public class QuestionController {


	@Autowired
	private QuestionService questionService;


	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	DifficultyService difService;
	@Autowired
	LangService langService;
	@Autowired
	ReCaptchaImpl reCaptcha;


	@RequestMapping(value="q.html", method=RequestMethod.GET)
	public String getNext(
			@ModelAttribute("questionSelector") QuestionSelector selector,
			Model model){
		try{
			Question nextQuestion = questionService.getNext(selector);
			if (nextQuestion != null){
				model.addAttribute("id",           nextQuestion.getId());
				model.addAttribute("question",     nextQuestion.getQuestion());
				model.addAttribute("answer",       nextQuestion.getAnswer());
				model.addAttribute("attachment",   nextQuestion.getAttachment());
				model.addAttribute("rating",       nextQuestion.calculateRating());
				return "question";
			}else{
				model.addAttribute(NameOf.MA_ERROR_MSG, "No next question found");
				return "error";
			}
		}catch (Exception e){
			model.addAttribute(NameOf.MA_ERROR_MSG, e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value="add.html", method = RequestMethod.GET)
	public String add(
			@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity,
			Model model
			){
		if ((!identity.equals(NameOf.NOTHING)) && userService.validateIdentity(identity)){
			model.addAttribute("questionSuggestion", new QuestionSuggestion());
			model.addAttribute("categories",   categoryService.getAll());
			model.addAttribute("difficulties", difService.getAll());
			model.addAttribute("languages",    langService.getAll());
			return "add";
		}else{
			model.addAttribute(new UserCreds());
			return "login";
		}
	}

	@RequestMapping(value="add.html",method = RequestMethod.POST)
	public String processSuggestion(
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			@ModelAttribute("questionSuggestion") QuestionSuggestion suggestion,
			ServletRequest servletRequest,
			Model model
			){
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			try{
				Long resultId = questionService.tryAddSuggested(suggestion);
				if (resultId != null){
					model.addAttribute(NameOf.MA_SUCCESS_MSG, "Question is added and will soon be moderated.");
					model.addAttribute("questionSuggestion", new QuestionSuggestion());
				}
			}catch(Exception e){
				model.addAttribute(NameOf.MA_ERROR_MSG, e.getMessage());
			}
		}else{
			model.addAttribute(NameOf.MA_ERROR_MSG, "wrong captcha");
		}
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("difficulties", difService.getAll());
		model.addAttribute("languages", langService.getAll());
		return "add";
	}


}
