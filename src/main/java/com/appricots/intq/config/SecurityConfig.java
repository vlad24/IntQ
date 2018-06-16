package com.appricots.intq.config;

import com.appricots.intq.NameOf;
import com.appricots.intq.services.IntqUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) {
        authBuilder.authenticationProvider(authenticationProvider());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/main.html").permitAll()
                .antMatchers("/register.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/login/perform")
                    .usernameParameter("login")
                    .passwordParameter("passHash")
                    .defaultSuccessUrl("/start.html")
                    .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout.html").permitAll()
                .logoutSuccessUrl("/login.html").permitAll()
                .and()
                .csrf().disable();
    }


    @Bean("authenticationProvider")
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


    @Bean
    UserDetailsService customUserDetailsService() {
        return new IntqUserDetailsService();
    }

    /*
        TEST RELATED BEANS
     */
    @Bean
    public PasswordEncoder testPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }


            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }


    /*
        PRODUCTION RELATED BEANS
    */
    @Bean
    @Profile(NameOf.Profile.PRODUCTION)
    public PasswordEncoder productionPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}