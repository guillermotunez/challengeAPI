package com.challenge.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
//        security.authorizeRequests(authz -> authz
//                .antMatchers(HttpMethod.GET, "/holaMundo/**").hasAuthority("SCOPE_read")
//                //.antMatchers(HttpMethod.POST, "/foos").hasAuthority("SCOPE_write")
//                .anyRequest().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    }
}