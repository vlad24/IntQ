package com.appricots.intq.wrappers;

import com.appricots.intq.model.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@ToString
public class IntqUserDetails implements UserDetails {

    private User user;


    public IntqUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(toList());
    }


    @Override
    public String getPassword() {
        return user.getCredentials().getPassHash();
    }


    @Override
    public String getUsername() {
        return user.getCredentials().getLogin();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsBlocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return !user.getIsDeleted();
    }


    public User getUser() {
        return user;
    }


}
