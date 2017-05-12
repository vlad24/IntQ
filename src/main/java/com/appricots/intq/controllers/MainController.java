package com.appricots.intq.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.QuestionSelector;


@Controller
public class MainController {
	

	private static final Log log = LogFactory.getLog(MainController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	
	public MainController(UserService service) {
		this.userService = service;
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String enter(
			@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		if (!identity.equals(NameOf.NOTHING)){
			if (userService.validateIdentity(identity)){
				return "main";
			}
		}
		return "main";
		
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String startListing(
			@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("questionSelector", new QuestionSelector());
		if (!identity.equals(NameOf.NOTHING)){
			if (userService.validateIdentity(identity)){
				UserSession lastSession = userService.getLastSession();
				model.addAttribute("question", lastSession.getLastQuestion());
				return "question";
			}
		}
		return "starter";
		
	}
}
	