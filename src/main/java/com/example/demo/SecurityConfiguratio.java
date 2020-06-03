package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Configuration
@EnableWebSecurity
public class SecurityConfiguratio extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    //bring up the form for log in + define who sees the form

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user")
                .password(passwordEncoder().encode("user"))
                .authorities("USER");
    }


}
