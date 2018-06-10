package com.appricots.intq.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.Question;
import com.appricots.intq.services.QuestionService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.AliasedId;
import com.appricots.intq.wrappers.UserProfile;

@Controller
public class AdminController {


	@Autowired
	UserService userService;

	@Autowired
	QuestionService questionService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="admin.html", method=RequestMethod.GET)
	public String admin(Model model){
		return "admin";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="admin_init.html", method=RequestMethod.GET)
	public String adminInit(Model model){
		try{
			List<UserProfile> newProfiles = userService.debugInit();
			List<Question> newQuestions = questionService.debugInit();
			StringBuilder builder = new StringBuilder();
			builder.append("Auto generated users:").append("<br>");
			for (UserProfile userProfile : newProfiles) {
				builder
				.append("* ")
				.append(userProfile.getLogin()).append(",").append(userProfile.getPass()).append("<br>");
			}
			builder.append("Auto generated questions:").append("<br>");
			for (Question question: newQuestions) {
				String qContent = question.getQuestion();
				builder
				.append("* ")
				.append(qContent.substring(0, Math.min(20, qContent.length()))).append("...")
				.append(" put into ").
				append(question.getCategories()).append("<br>");
			}
			model.addAttribute(NameOf.MA_SUCCESS_MSG, builder.toString());
		}catch (org.hibernate.exception.ConstraintViolationException alreadyInited){
			model.addAttribute(NameOf.MA_ERROR_MSG, "Some entities that were tries to be added are already in database.");
		}catch (Exception e){
			model.addAttribute(NameOf.MA_ERROR_MSG, e.getMessage());
		}
		return "control";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="admin_moderate.html", method=RequestMethod.GET)
	public String adminModerate(Model model){
        List<AliasedId<Long>> questions = questionService.getNew();
        model.addAttribute("questions", questions);
        return "new_questions";
	}
}
