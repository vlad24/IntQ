package com.appricots.intq.controllers;

import com.appricots.intq.services.QuestionService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.AliasedId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {


	@Autowired
    UserService userService;

	@Autowired
	QuestionService questionService;

	@Secured("ROLE_ADMIN_ACCESS")
	@RequestMapping(value="admin.html", method=RequestMethod.GET)
	public String admin(Model model){
		return "admin";
	}


	@Secured({"ROLE_ADMIN_ACCESS", "ROLE_MODERATOR_ACCESS"})
	@RequestMapping(value="admin_moderate.html", method=RequestMethod.GET)
	public String adminModerate(Model model){
        List<AliasedId<Long>> questions = questionService.getNew();
        model.addAttribute("questions", questions);
        return "new_questions";
	}
}
