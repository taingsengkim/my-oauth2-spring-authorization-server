package co.istad.oauth2springauthorizationserver2.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class RegisteredClientInitializer {

    @Bean
    ApplicationRunner initializeRegisteredClient(
            RegisteredClientRepository registeredClientRepository) {

        return args -> {
            if (registeredClientRepository.findByClientId("oidc-client") != null) {
                return;
            }

            RegisteredClient oidcClient = RegisteredClient
                    .withId(UUID.randomUUID().toString())
                    .clientId("oidc-client")
                    .clientSecret("{noop}secret")
                    .clientName("OIDC Client")
                    .clientAuthenticationMethod(
                            ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(
                            AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(
                            AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri(
                            "http://127.0.0.1:8080/login/oauth2/code/oidc-client")
                    .postLogoutRedirectUri("http://127.0.0.1:8080/")
                    .scope(OidcScopes.OPENID)
                    .scope(OidcScopes.PROFILE)
                    .clientSettings(ClientSettings.builder()
                            .requireAuthorizationConsent(true)
                            .build())
                    .build();

            registeredClientRepository.save(oidcClient);
        };
    }
}