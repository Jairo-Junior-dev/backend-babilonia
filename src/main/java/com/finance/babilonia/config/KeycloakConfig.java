package com.finance.babilonia.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {


    private final String clientId;

    private final String realm;

    private final String username;

    private final String serveUrl;

    private final String password;

    public KeycloakConfig(@Value("${keycloak.client-id}") String clientId, @Value("${keycloak.realm}") String realm,
                          @Value("${keycloak.username}") String username, @Value("${keycloak.server-url}") String serveUrl,
                          @Value("${keycloak.password}") String password) {

        this.clientId = clientId;
        this.realm = realm;
        this.username = username;
        this.serveUrl = serveUrl;
        this.password = password;
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .clientId(clientId)
                .realm(realm)
                .username(username)
                .password(password)
                .serverUrl(serveUrl)
                .build();
    }
}
