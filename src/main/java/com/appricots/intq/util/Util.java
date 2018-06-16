package com.appricots.intq.util;

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
}
