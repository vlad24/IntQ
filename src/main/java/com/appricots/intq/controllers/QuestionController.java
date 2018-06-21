package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Question;
import com.appricots.intq.services.*;
import com.appricots.intq.util.Util;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import com.appricots.intq.wrappers.reqobjects.QuestionSuggestion;
import com.appricots.intq.wrappers.respobjects.QuestionResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletRequest;

@EnableWebMvc
@Controller
public class QuestionController {

	private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private DifficultyService difService;
	@Autowired
	private LangService langService;
	@Autowired
	private ReCaptchaImpl reCaptcha;

	@RequestMapping(value="q.html", method=RequestMethod.GET)
	public String getNext(@ModelAttribute("questionSelector") QuestionSelector questionSelector, Model model){
		model.addAttribute("questionSelector", questionSelector);
		Util.fillModelWithUserName(model);
		return "question";
	}

	
	@ResponseBody
	@RequestMapping(value="nextQ", method=RequestMethod.GET, produces = "application/json")
	public QuestionResponse getNextQuestionAsJson(@ModelAttribute("questionSelector") QuestionSelector selector){
		try{
			Question nextQuestion = questionService.getNext(selector);
			if (nextQuestion != null){
				selector.setShift(selector.getShift() + 1);
				QuestionResponse questionResponse = QuestionResponse.fromQuestionAndSelector(nextQuestion, selector);
				return questionResponse;
			} else{
				return null;
			}
		}catch (Exception e){
			logger.error("Error occured while getting next question!", e);
			return null;
		}
	}

	@Secured({"ROLE_ADMIN_ACCESS", "ROLE_MODERATOR_ACCESS", "ROLE_USER_ACCESS"})
	@RequestMapping(value="suggestion.html", method = RequestMethod.GET)
	public ModelAndView getSuggestionForm(){
		ModelAndView mav = new ModelAndView();
		Util.fillModelAndViewWithUserName(mav);
		mav.addObject("questionSuggestion", new QuestionSuggestion());
		mav.addObject("categories",   categoryService.getAll());
		mav.addObject("difficulties", difService.getAll());
		mav.addObject("languages",    langService.getAll());
		mav.setViewName("suggestion");
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
