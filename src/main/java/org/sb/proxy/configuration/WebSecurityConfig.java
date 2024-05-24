package org.sb.proxy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login(Customizer.withDefaults())
                .authorizeHttpRequests(
                request -> request.requestMatchers("/").authenticated()
                        .anyRequest().authenticated()

        );
        return http.build();
    }
}
