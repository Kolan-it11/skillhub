package com.skillhub.skillhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/students").permitAll()
                        .requestMatchers("/api/users/teachers").permitAll()
                        .requestMatchers("/api/users/admins").permitAll()
                        .requestMatchers("/api/users/admins/**").hasRole("ADMIN")
                        .requestMatchers("/api/certificates/**").hasRole("ADMIN")
                        .requestMatchers("/api/assignments/**").hasRole("TEACHER")
                        .requestMatchers("/api/courses/**").hasRole("TEACHER")
                        .requestMatchers("/api/enrollments/**").hasRole("STUDENT")
                        .requestMatchers("/api/submissions/**").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}