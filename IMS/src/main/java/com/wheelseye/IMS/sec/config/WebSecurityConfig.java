package com.wheelseye.IMS.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wheelseye.IMS.service.EmployeeDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new EmployeeDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()			
			.antMatchers("/editinterviewee/*").hasAnyAuthority("HR")
			.antMatchers("/listinterviewee/*").hasAnyAuthority("HR")
			.antMatchers("/deleteinterviewee/*").hasAnyAuthority("HR")
			.antMatchers("/downloadFile/*").hasAnyAuthority("INTERVIEWER", "HR", "ADMIN")
			
			.antMatchers("/hr/**").hasAuthority("HR")
			.antMatchers("/interviewee/**").permitAll()
			
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/interviewer/**").hasAuthority("INTERVIEWER")
			
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
		
		http.formLogin().defaultSuccessUrl("/indexAccess",true);
	}
}
