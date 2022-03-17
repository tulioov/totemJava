package com.totem.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
    	.antMatchers("/oauth2/**", "/login/**", "/monitoramento").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login()
        .defaultSuccessUrl( "/" );
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/externo/**",  "/js/**", "/img/**", "/icon/**");
	}
    
}
