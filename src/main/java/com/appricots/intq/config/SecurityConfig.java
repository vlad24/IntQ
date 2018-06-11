package com.appricots.intq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) {
        authBuilder.authenticationProvider(authenticationProvider());
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