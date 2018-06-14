package com.appricots.intq.services;

import com.appricots.intq.dao.impl.UserDAO;
import com.appricots.intq.model.IntqUserDetails;
import com.appricots.intq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    UserDAO userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByLogin(username);
        return new IntqUserDetails(user);
    }


}
