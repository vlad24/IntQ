package com.appricots.intq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) {
        authBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                    .formLogin()
                        .loginPage("/login.html")
                        .loginProcessingUrl("/j_spring_security_check")
                        .failureUrl("/login.html?error=true")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .permitAll()
                .and()
                    .httpBasic()
                .and()
                    .authorizeRequests()
                    .antMatchers("/secured/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasRole("USER")
                .and()
                    .logout()
                        .logoutUrl("/j_spring_security_logout")
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true)
                        .permitAll()
                .and()
                    .rememberMe()
                        .key("rememberMeKey")
                        .tokenValiditySeconds(300)
                .and()
                    .csrf()
                    .disable();
    }
}