package com.sparta.openmind.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //CSRF 설정
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/user/**").permitAll()
                        .anyRequest().authenticated());

        //로그인 사용
        http.formLogin((formLogin) -> formLogin
                .loginPage("api/user/login-page")
                .loginProcessingUrl("/api/user/login")
                .defaultSuccessUrl("/")
                .failureUrl("/api/user/login-page?error")
                .permitAll());
        return http.build();
    }
}
