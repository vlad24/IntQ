package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Question;
import com.appricots.intq.model.UserCredentials;
import com.appricots.intq.services.*;
import com.appricots.intq.util.SecurityUtil;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import com.appricots.intq.wrappers.reqobjects.QuestionSuggestion;
import com.appricots.intq.wrappers.respobjects.QuestionResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletRequest;

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
				if (SecurityUtil.getCurrentUser().isPresent()){
					// could fectch user session and store some data
					//UserSession session = userService.getCurrentSessionByCookie(identity);
				}
			}else{
				model.addAttribute(NameOf.ModelAttributeKey.NO_MORE_QUESTIONS_FLAG, true);
			}
			return "question";
		}catch (Exception e){
			model.addAttribute(NameOf.ModelAttributeKey.ERROR_MSG, e.getMessage());
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
	public ModelAndView getSuggestionForm(){
		ModelAndView mav = new ModelAndView();
		if (SecurityUtil.getCurrentUser().isPresent()){
			mav.addObject("questionSuggestion", new QuestionSuggestion());
			mav.addObject("categories",   categoryService.getAll());
			mav.addObject("difficulties", difService.getAll());
			mav.addObject("languages",    langService.getAll());
			mav.setViewName("suggestion");
		}else{
			mav.addObject("userCredentials", new UserCredentials());
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
					mav.addObject(NameOf.ModelAttributeKey.SUCCESS_MSG, "Question is added and will soon be moderated.");
					mav.addObject("questionSuggestion", new QuestionSuggestion());
				}
			}catch(Exception e){
				mav.addObject(NameOf.ModelAttributeKey.ERROR_MSG, e.getMessage());
			}
		}else{
			mav.addObject(NameOf.ModelAttributeKey.ERROR_MSG, "wrong captcha");
		}
		mav.addObject("categories", categoryService.getAll());
		mav.addObject("difficulties", difService.getAll());
		mav.addObject("languages", langService.getAll());
		mav.setViewName("suggestion");
		return mav;
	}


}
