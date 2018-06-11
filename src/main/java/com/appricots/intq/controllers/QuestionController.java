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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Question;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.DifficultyService;
import com.appricots.intq.services.LangService;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import com.appricots.intq.wrappers.reqobjects.QuestionSuggestion;
import com.appricots.intq.wrappers.respobjects.QuestionResponse;

@EnableWebMvc
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
	public String getNext(@ModelAttribute("questionSelector") QuestionSelector selector, Model model){
		try{
			Question nextQuestion = questionService.getNext(selector);
			if (nextQuestion != null){
				selector.setShift(selector.getShift() + 1);
				model.addAttribute("id",               nextQuestion.getId());
				model.addAttribute("question",         nextQuestion.getQuestion());
				model.addAttribute("answer",           nextQuestion.getAnswer());
				model.addAttribute("attachment",       nextQuestion.getAttachment());
				model.addAttribute("rating",           nextQuestion.calculateRating());
				model.addAttribute("questionSelector", selector);
				if (userService.getCurrentUser().isPresent()){
					// could fectch user session and store some data
					//UserSession session = userService.getCurrentSessionByCookie(identity);
				}
			}else{
				model.addAttribute(NameOf.MA_NO_MORE_QUESTIONS, true);
			}
			return "question";
		}catch (Exception e){
			model.addAttribute(NameOf.MA_ERROR_MSG, e.getMessage());
			return "error";
		}
	}

	
	@ResponseBody
	@RequestMapping(value="nextQ", method=RequestMethod.GET, produces = "application/json")
	public QuestionResponse getNextQuestionAsJson(
			@ModelAttribute("questionSelector") QuestionSelector selector
			//@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity
		){
		try{
			Question nextQuestion = questionService.getNext(selector);
			if (nextQuestion != null){
				selector.setShift(selector.getShift() + 1);
				QuestionResponse r = QuestionResponse.fromQuestionAndSelector(nextQuestion, selector);
				return r;
			}else{
				return null;
			}
		}catch (Exception e){
			return null;
		}
	}

	@RequestMapping(value="suggestion.html", method = RequestMethod.GET)
	public ModelAndView getSuggestionForm(Model model){
		ModelAndView mav = new ModelAndView();
		if (userService.getCurrentUser().isPresent()){
			mav.addObject("questionSuggestion", new QuestionSuggestion());
			mav.addObject("categories",   categoryService.getAll());
			mav.addObject("difficulties", difService.getAll());
			mav.addObject("languages",    langService.getAll());
			mav.setViewName("suggestion");
		}else{
			mav.addObject(new UserCreds());
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping(value="suggest",method = RequestMethod.POST)
	public ModelAndView processSuggestion(
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			@ModelAttribute("questionSuggestion") QuestionSuggestion suggestion,
			ServletRequest servletRequest,
			Model model
			){
		ModelAndView mav = new ModelAndView();
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			try{
				Long resultId = questionService.tryAddSuggested(suggestion);
				if (resultId != null){
					mav.addObject(NameOf.MA_SUCCESS_MSG, "Question is added and will soon be moderated.");
					mav.addObject("questionSuggestion", new QuestionSuggestion());
				}
			}catch(Exception e){
				mav.addObject(NameOf.MA_ERROR_MSG, e.getMessage());
			}
		}else{
			mav.addObject(NameOf.MA_ERROR_MSG, "wrong captcha");
		}
		mav.addObject("categories", categoryService.getAll());
		mav.addObject("difficulties", difService.getAll());
		mav.addObject("languages", langService.getAll());
		mav.setViewName("suggestion");
		return mav;
	}


}
