package com.totem.config;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends AADWebSecurityConfigurerAdapter{
	@Value( "${app.protect.authenticated}" )
	private String[] protectedRoutes;

    @Override
    public void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http.authorizeRequests()
		.antMatchers(protectedRoutes).authenticated()
     	.antMatchers("/**").permitAll()
     	.and()
     	.csrf().disable()
     	.headers().frameOptions().disable();
    
    http.logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/logout.done")
    .deleteCookies("JSESSIONID")
    .invalidateHttpSession(true);
    }
   
}
