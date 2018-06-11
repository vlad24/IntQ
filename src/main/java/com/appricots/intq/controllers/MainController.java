package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.model.User;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.DifficultyService;
import com.appricots.intq.services.LangService;
import com.appricots.intq.services.UserService;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DifficultyService diffService;
    @Autowired
    private LangService langService;


    @Secured("ROLE_USER")
    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String enter(Model model) {
        User user = userService.getCurrentUser();
        if (user != null) {
            model.addAttribute(NameOf.MA_USERNAME, user.getCreds().getLogin());
        }
        return "main";

    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startListing(Model model) {
        User user = userService.getCurrentUser();
        if (user != null) {
            model.addAttribute(NameOf.MA_USERNAME, user.getCreds().getLogin());
//            UserSession lastSession = userService.getgetCurrentSessionByCookie(identity);
//            if (lastSession != null){
//                //could do something useful
//            }
        }
        model.addAttribute(NameOf.MA_CATEGORIES, categoryService.getAliasedIds());
        model.addAttribute(NameOf.MA_DIFFICULTIES, diffService.getAliasedIds());
        model.addAttribute(NameOf.MA_LANGS, langService.getAliasedIds());
        model.addAttribute(NameOf.MA_QUESTION_SELECTOR, new QuestionSelector());
        return "starter";
    }


}
	