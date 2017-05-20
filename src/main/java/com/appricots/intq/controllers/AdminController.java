package com.appricots.intq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appricots.intq.NameOf;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.services.UserService;

@Controller
public class AdminController {


	@Autowired
	UserService userService;
	
	@Autowired
	QuestionService questionService;
	
	
	@RequestMapping(value="admin.html", method=RequestMethod.GET)
	public String admin(
			@CookieValue(value = NameOf.COOKIE_4_ADMIN, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		if (identity.equals(NameOf.DEBUG_ADMIN_COOKIE) || true){
			return "admin";
		}
		return "main";
	}
	
	@RequestMapping(value="admin_init.html", method=RequestMethod.GET)
	public String adminInit(
			@CookieValue(value = NameOf.COOKIE_4_ADMIN, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		System.out.println("debug init...");
		if (identity.equals(NameOf.DEBUG_ADMIN_COOKIE) || true){
			StringBuilder resultMsg = new StringBuilder();
			userService.debugInit();
			resultMsg.append("Added debug user.");
			questionService.debugInit();
			model.addAttribute(NameOf.MA_SUCCESS_MSG, "added debug user");
			return "admin";
		}
		return "main";
	}
	
	@RequestMapping(value="admin_moderate.html", method=RequestMethod.GET)
	public String adminModerate(
			@CookieValue(value = NameOf.COOKIE_4_ADMIN, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		System.out.println("moderating...");
		if (identity.equals(NameOf.DEBUG_ADMIN_COOKIE) || true){
			return "admin";
		}
		return "main";
	}
}
