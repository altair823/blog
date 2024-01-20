package org.altair823.blog.config.auth;

import lombok.RequiredArgsConstructor;
import org.altair823.blog.domain.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/posts/{id}").permitAll()
                                .requestMatchers("/posts-save", "/api/v1/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated())
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/"))
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint
                                                .userService(customOAuth2UserService)))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));


        return http.getOrBuild();
    }
}
