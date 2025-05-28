package com.dobval.SecureChat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dobval.SecureChat.security.JwtAuthenticationFilter;
import com.dobval.SecureChat.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired 
    private CustomUserDetailsService userDetailsService;
    @Autowired 
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception {
        http
          .securityMatcher("/login", "/auth/**", "/js/**", "/css/**", "/chat/**")
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(a -> a.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    SecurityFilterChain protectedEndpoints(HttpSecurity http,
                                           JwtAuthenticationFilter jwtFilter) throws Exception {
        http
          .securityMatcher("/api/**") //protect all api endpoints
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(a -> a.anyRequest().authenticated())
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
          .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
