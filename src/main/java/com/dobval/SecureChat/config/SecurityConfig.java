package com.dobval.SecureChat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { //TEMP to test Without CSRF, TODO: Remove

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          // Disable CSRF so curl can POST without token
          .csrf(csrf -> csrf.disable())
          // Disable HTTP Basic (or you can leave it on if you want auth)
          .httpBasic(httpBasic -> httpBasic.disable())
          // Allow all requests without authentication
          .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
