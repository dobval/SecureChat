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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(
            	            "/auth/**",        // allow login/register API
            	            "/login",          // login page
            	            "/chat",           // chat page (if using frontend routing)
            	            "/js/**",          // static JS
            	            "/css/**",         // static CSS
            	            "/favicon.ico"     // TODO: ADD FAVICON
            	        ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login-page")
                    .loginProcessingUrl("/login")  // where the login form POSTs to
                    .defaultSuccessUrl("/chat", true)
                    .permitAll())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
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

/* DISABLES SECURITY
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
*/
