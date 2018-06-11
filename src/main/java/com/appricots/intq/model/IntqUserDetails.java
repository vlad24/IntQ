package com.appricots.intq.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class IntqUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;


    public IntqUserDetails(User user) {
        this.user = user;
    }


    public User getIntqUser() {
        return user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return user.getCreds().getPassHash();
    }


    @Override
    public String getUsername() {
        return user.getCreds().getLogin();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
