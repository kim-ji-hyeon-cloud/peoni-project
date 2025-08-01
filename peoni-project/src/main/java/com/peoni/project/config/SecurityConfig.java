package com.peoni.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailService customUserDetailService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/product/register").hasRole("ADMIN")
					.requestMatchers("/member/register", "/member/checkId", "/member/findIdPost", "/member/findPwPost").permitAll()
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
					.anyRequest().permitAll()
			)
			.formLogin(form -> form
					.loginPage("/member/login")
					.loginProcessingUrl("/member/loginPost")
					.defaultSuccessUrl("/main", true)
					.usernameParameter("userId")
					.passwordParameter("userPw")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutSuccessUrl("/main")
					.permitAll());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
}











