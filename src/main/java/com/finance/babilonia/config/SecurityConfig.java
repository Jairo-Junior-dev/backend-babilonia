package com.finance.babilonia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.authorizeHttpRequests(
                        http -> http.
                                requestMatchers("/api/user/**").permitAll().
                                requestMatchers("/swagger-ui.html").permitAll().
                                requestMatchers("/v3/api-docs/**").permitAll().
                                requestMatchers("/swagger-ui/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
                .csrf(CsrfConfigurer::disable)
                .build();
    }
}
