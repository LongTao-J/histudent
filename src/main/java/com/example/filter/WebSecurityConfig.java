package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("==========WebSecurityConfig============WebSecurityConfigurerAdapter");
        //预请求放行
        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token",
                        "/datashare/**", "/api/**")
                .and()
                // 跨域
                .cors()
                .and()
                // 禁用csrf
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(
                "/ws/**"
        );
    }
}