package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource; 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	            .antMatchers("/").permitAll()  // allow public access to home page
	            .antMatchers("/employees").hasRole("EMPLOYEE")
	            .antMatchers("/leaders/**").hasRole("MANAGER")
	            .antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin().loginPage("/showMyLoginPage") // custom page
				.loginProcessingUrl("/authenticateTheUser")
				.defaultSuccessUrl("/employees")
				.permitAll() // allow all to see this page
			.and()
			.logout()
			.logoutSuccessUrl("/showMyLoginPage")  // after logout then redirect to landing page (root)
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}

	
}
