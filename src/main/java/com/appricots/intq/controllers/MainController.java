package com.appricots.intq.controllers;

import com.appricots.intq.NameOf;
import com.appricots.intq.services.CategoryService;
import com.appricots.intq.services.DifficultyService;
import com.appricots.intq.services.LangService;
import com.appricots.intq.wrappers.reqobjects.QuestionSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DifficultyService diffService;
    @Autowired
    private LangService langService;


    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String main() {
        return "main";
    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startListing(Model model) {
        model.addAttribute(NameOf.ModelAttributeKey.CATEGORIES, categoryService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.DIFFICULTIES, diffService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.LANGUAGES, langService.getAliasedIds());
        model.addAttribute(NameOf.ModelAttributeKey.QUESTION_SELECTOR, new QuestionSelector());
        return "starter";
    }


}
	