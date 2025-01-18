package com.example.food.config;

import com.example.food.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors(cors -> cors
                        .configurationSource(CorsConfig.corsConfigurationSource())
                )
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)

                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/check-id"
                                , "/api/auth/check-nickname"
                                , "/api/auth/signup"
                                , "/api/auth/login"
                                , "/api/search").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // jwtAuthenticationFilter -> 요청 header에 토큰을 검사
        // UsernamePasswordAuthenticationFilter를 사용하지 않을 것이므로 jwt 필터를 먼저 실행

        return httpSecurity.build();
    }


}
