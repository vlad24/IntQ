package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.User;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.DifficultyService;
import com.appricots.intq.services.LangService;
import com.appricots.intq.util.SecurityUtil;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class MainController {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DifficultyService diffService;
    @Autowired
    private LangService langService;


    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String enter(Model model) {
        Optional<User> user = SecurityUtil.getCurrentUser();
        user.ifPresent(u -> model.addAttribute(NameOf.ModelAttributeKey.USERNAME, u.getCredentials().getLogin()));
        return "main";

    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startListing(Model model) {
        Optional<User> user = SecurityUtil.getCurrentUser();
        //            UserSession lastSession = userService.getgetCurrentSessionByCookie(identity);
        //            if (lastSession != null){
        //                //could do something useful
        //            }
        user.ifPresent(u -> model.addAttribute(NameOf.ModelAttributeKey.USERNAME, u.getCredentials().getLogin()));
        model.addAttribute(NameOf.ModelAttributeKey.CATEGORIES, categoryService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.DIFFICULTIES, diffService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.LANGUAGES, langService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.QUESTION_SELECTOR, new QuestionSelector());
        return "starter";
    }


}
	