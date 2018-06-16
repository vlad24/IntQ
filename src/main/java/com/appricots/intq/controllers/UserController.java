package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.User;
import com.appricots.intq.model.UserCredentials;
import com.appricots.intq.services.UserService;
import com.appricots.intq.util.SecurityUtil;
import com.appricots.intq.wrappers.UserProfile;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	ReCaptchaImpl reCaptcha;

	public UserController(UserService dao) {
		userService = dao;
	}

	@RequestMapping(value="login.html", method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("userCreds", new UserCredentials());
		return "login";		
	}

	@RequestMapping(value="login/perform", method = RequestMethod.POST)
	public String startSession(@ModelAttribute("userCredentials") UserCredentials userCredentials,
			HttpServletResponse response,
			Model model
			){
		try {
			if (userService.getUserByCredentials(userCredentials) != null){
				String cookie = userService.startNewSession(userCredentials.getLogin());
				if (cookie != null){
					response.addCookie(new Cookie(NameOf.COOKIE_4_IDENTITY, cookie));
					return "redirect:/start.html";
				}else{
					throw new Exception("Could not start session");
				}
			}else{
				throw new Exception("Could not find user");
			}
		} catch (Exception e) {
		    logger.error("Error!", e);
			model.addAttribute(NameOf.ModelAttributeKey.ERROR_MSG, e.getMessage());
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
		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		try {
			if(reCaptchaResponse.isValid()){
				Long registerUser = userService.registerUser(profile);
				if (!(registerUser == null)){
					String cookie = userService.startNewSession(profile.getLogin());
					if (!(cookie == null)){
						response.addCookie(new Cookie(NameOf.COOKIE_4_IDENTITY, cookie));
						return "redirect:/start.html";
					}else{
						throw new Exception("Unable to start new session");
					}
				}else{
					throw new Exception("Could not write your data to database");
				}
			}else{
				throw new Exception("Wrong captcha!");
			}
		} catch (Exception e) {
			model.addAttribute(NameOf.ModelAttributeKey.ERROR_MSG, e.getMessage());
			return "register";
		}

	}

	@RequestMapping(value="profile.html",method = RequestMethod.GET)
	public String getProfile(Model model){
		Optional<User> user = SecurityUtil.getCurrentUser();
		if (user.isPresent()){
			model.addAttribute("login", user.get().getCredentials().getLogin());
			model.addAttribute("pass", user.get().getCredentials().getPassHash());
			model.addAttribute("email", user.get().getEmail());
			model.addAttribute("lastName", user.get().getLastName());
			model.addAttribute("age", user.get().getAge());
			model.addAttribute("activeness", user.get().getActiveness());
			return "userInfo";
		}else{
			model.addAttribute(new UserCredentials());
			return "login";
		}
	}

	@RequestMapping(value="logout.html",method = RequestMethod.GET)
	public String dropSession(Model model){
		Optional<User> user = SecurityUtil.getCurrentUser();
		user.ifPresent(u -> userService.dropSession(u.getId()));
		return "main";
	}
}
