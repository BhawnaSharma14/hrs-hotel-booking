package com.hrs.hotelbooking.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Bhawna-pc
 * */
@Configuration
@EnableWebSecurity
public class ApplicationConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/hrs-hotels/**", "/swagger-ui/**")
                .authorizeHttpRequests(authz -> authz.requestMatchers(
                        "/hrs-hotels/**","/swagger-ui/**", "/get-all-bookings/**",
                        "/v3/api-docs/**", "/book/**"
                ,"/cancel/**", "/get-by-booking-id/**" ).permitAll().anyRequest().authenticated());

        return httpSecurity.build();
    }
}
