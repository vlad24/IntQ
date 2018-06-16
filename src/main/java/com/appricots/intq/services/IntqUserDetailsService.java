package com.appricots.intq.services;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.wrappers.IntqUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class IntqUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new IntqUserDetails(
                userDAO.getByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }

}
