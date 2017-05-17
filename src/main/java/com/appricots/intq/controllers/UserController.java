package com.appricots.intq.controllers;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCreds;
import com.appricots.intq.model.UserSession;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.UserProfile;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ReCaptchaImpl reCaptcha;

	public UserController(UserService dao) {
		userService = dao;
	}

	@RequestMapping(value="login.html", method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute(new UserCreds());
		return "login";		
	}
	
	@RequestMapping(value="login.html", method = RequestMethod.POST)
	public String startSession(@ModelAttribute("userCreds") UserCreds creds,
			HttpServletResponse response,
			Model model
			){
		User user = userService.getUserForCreds(creds);
		if (user != null){
			UserSession newSession = userService.startNewSession(creds.getLogin());
			System.out.print("Started new session");
			response.addCookie(new Cookie(NameOf.COOKIE_4_IDENTITY, newSession.getIdentCookie()));
			return "redirect:/start.html";
		}else{
			model.addAttribute(NameOf.ERROR_MSG, "User not found. Try again");
		}
		return "login";
	}

	@RequestMapping(value="register.html",method = RequestMethod.GET)
	public String startRegistering(Model model){
		model.addAttribute("profile", new UserProfile());
		return "register";
	}
	
	
	@RequestMapping(value="register.html",method = RequestMethod.POST)
	public String registerNewUser(
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field")  String responseField,
			@ModelAttribute("profile") UserProfile profile,
			ServletRequest servletRequest,
			HttpServletResponse response,
			Model model){
		System.out.println(profile);
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		if(reCaptchaResponse.isValid()){
			if (userService.registerUser(profile) != null){
				UserSession newSession = userService.startNewSession(profile.getLogin());
				response.addCookie(new Cookie(NameOf.COOKIE_4_IDENTITY, newSession.getIdentCookie()));
				return "redirect:/start.html";
			}else{
				model.addAttribute(NameOf.ERROR_MSG, "Such user exists");
				return "register";
			}
		}else{
			model.addAttribute(NameOf.ERROR_MSG, "Wrong captcha");
			return "register";
		}
		
	}
	
	@RequestMapping(value="profile.html",method = RequestMethod.GET)
	public String getProfile(
			@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity,
			Model model){
		User user = userService.getUserForIdentity(identity);
		if (user != null){
			model.addAttribute("login", user.getCreds().getLogin());
			model.addAttribute("pass", user.getCreds().getPassHash());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("name", user.getName());
			model.addAttribute("lastName", user.getName());
			model.addAttribute("age", user.getAge());
			model.addAttribute("activeness", user.getActiveness());
			return "userInfo";
		}else{
			model.addAttribute(new UserCreds());
			return "login";
		}
	}
	
	@RequestMapping(value="logout.html",method = RequestMethod.GET)
	public String dropSession(
			@CookieValue(value = NameOf.COOKIE_4_IDENTITY, defaultValue = NameOf.NOTHING) String identity,
			Model model){
		if (userService.validateIdentity(identity)){
			userService.dropSession(identity);
		}else{
		
		}
		return "main";
	}
}
