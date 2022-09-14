package com.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig{

	// add a reference to our security DataSource
	private DataSource securityDataSource;
	
	@Autowired
	public DemoSecurityConfig(DataSource theSecurityDataSource) {
		securityDataSource = theSecurityDataSource;
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		return new JdbcUserDetailsManager(securityDataSource);
	}
	
	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
            	.antMatchers("/").hasRole("EMPLOYEE")
            	.antMatchers("/leaders/**").hasRole("MANAGER")
            	.antMatchers("/systems/**").hasRole("ADMIN")
            .and()	
            .formLogin()
            	.loginPage("/showMyLoginPage")
            	.loginProcessingUrl("/authenticateTheUser")
            	.permitAll()
            .and()
            .logout()
            	.permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//    	
//        UserDetails john = User.builder()
//            .username("john")
//            .password("{noop}test123")
//            .roles("EMPLOYEE")
//            .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","ADMIN")
//                .build();
//        
//        return new InMemoryUserDetailsManager(john, mary, susan);  
//    }
	
	
}
