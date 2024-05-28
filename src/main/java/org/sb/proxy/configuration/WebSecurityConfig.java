package org.sb.proxy.configuration;

import com.azure.spring.cloud.autoconfigure.implementation.aad.configuration.properties.AadAuthenticationProperties;
import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadClientRegistrationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;


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

    @Bean
    WebClient webClient(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction function =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(oAuth2AuthorizedClientManager);
        return WebClient.builder()
                .apply(function.oauth2Configuration())
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(AadAuthenticationProperties aadAuthenticationProperties) {
        // fetch the secrets for app registration
        String secret = aadAuthenticationProperties.getCredential().getClientSecret();
        System.out.println("This is the secret: " + secret);
        return new AadClientRegistrationRepository(aadAuthenticationProperties);
    }
}
