package com.appricots.intq.security;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static java.util.stream.Collectors.toList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDAO userDAO;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userDAO.getByLogin(authentication.getName());
        return new UsernamePasswordAuthenticationToken(
                authentication.getName(),
                authentication.getCredentials(),
                user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(toList())
        );
    }


    public boolean supports(Class<?> aClass) {
        System.out.println(aClass.getSimpleName());
        return true;
    }
}
