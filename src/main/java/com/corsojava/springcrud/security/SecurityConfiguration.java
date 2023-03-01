package com.corsojava.springcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				// CREA e MODIFICA only ADMIN
			.requestMatchers("/pizze/insert", "/pizze/insert/**").hasAuthority("ADMIN")
				// DELETE only ADMIN
			.requestMatchers(HttpMethod.POST, "/pizze/**").hasAuthority("ADMIN")
				// INGREDIENTI only ADMIN
			.requestMatchers("/ingredienti", "/ingredienti/**").hasAuthority("ADMIN")
				// OFFERTE only ADMIN
			.requestMatchers("/offerte", "/offerte/**").hasAuthority("ADMIN")
			
			.requestMatchers("/pizze", "/pizze/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/**").permitAll()
			.and().formLogin()
			.and().logout()
			.and().exceptionHandling()
			.accessDeniedPage("/access-denied.html");
		return http.build();
	}
	
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();	
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
//		System.out.println("USER: "+passwordEncoder().encode("user"));
//	    System.out.println("ADMIN: "+passwordEncoder().encode("admin"));
		
		return authProvider;
	}


}
