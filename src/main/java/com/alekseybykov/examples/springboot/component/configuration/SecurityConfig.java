package com.alekseybykov.examples.springboot.component.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("admin")
               .password("{noop}admin")
               .roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html**",
            "/webjars/**",
            "/application/actuator/**",
            "/application/browser/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "/application/actuator/**",
                        "/application/browser/**").anonymous()

                .antMatchers(HttpMethod.GET, "/person/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/person/add").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/person/update").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/person/**").hasRole("USER")
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable();
    }
}