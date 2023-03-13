package com.KJS.LoginServices.Security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.KJS.LoginServices.Service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Autowired
	private Environment environment;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		// Configure AuthenticationManagerBuilder
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);

	

		http.cors().and().csrf().disable().authorizeHttpRequests().
			requestMatchers(HttpMethod.POST, "/users").permitAll()
				.requestMatchers(HttpMethod.GET, "*").permitAll()
				//.requestMatchers(HttpMethod.GET, "/mylogin").authenticated()
				.requestMatchers(HttpMethod.OPTIONS, "*").permitAll()
				.requestMatchers(HttpMethod.PUT, "*").permitAll().and();

		return http.build();
	}

	@Bean
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8082", "http://localhost:9002","*"));
		config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "OPTIONS"));

		config.setAllowCredentials(true);
		config.setAllowedHeaders(List.of("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		config.setMaxAge(3600L);

		return config;
	}

}
