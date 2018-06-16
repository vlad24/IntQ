package com.appricots.intq.util;

import com.appricots.intq.NameOf;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.function.Function;

public class Util {


    public static <T> T transformNonEmptyRequestParamOrDefault(String key, Function<String, T> transformer, T defaultValue, ServletRequest request){
        String value = request.getParameter(key);
        if (value != null) {
            return transformer.apply(value);
        } else {
            return defaultValue;
        }
    }

    public static  <T> T nvl(T value, T defaultValue){
        if (value == null){
            return defaultValue;
        } else {
            return defaultValue;
        }
    }


    public static void fillModelWithUserName(Model model) {
        SecurityUtil.getCurrentUser().ifPresent(user -> model.addAttribute(NameOf.ModelAttributeKey.USERNAME, user.getCredentials().getLogin()));
    }

    public static void fillModelAndViewWithUserName(ModelAndView modelAndView) {
        SecurityUtil.getCurrentUser().ifPresent(user -> modelAndView.addObject(NameOf.ModelAttributeKey.USERNAME, user.getCredentials().getLogin()));
    }
}
