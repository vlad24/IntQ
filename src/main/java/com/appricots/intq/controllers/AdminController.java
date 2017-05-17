package com.appricots.intq.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appricots.intq.NameOf;

public class AdminController {


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
		if (identity.equals(NameOf.DEBUG_ADMIN_COOKIE) || true){
			return "admin";
		}
		return "main";
	}
	
	@RequestMapping(value="admin_moderate.html", method=RequestMethod.GET)
	public String adminModerate(
			@CookieValue(value = NameOf.COOKIE_4_ADMIN, defaultValue = NameOf.NOTHING) String identity, 
			Model model
			){
		if (identity.equals(NameOf.DEBUG_ADMIN_COOKIE) || true){
			return "admin";
		}
		return "main";
	}
}
