package gawlowski.maciej.example.config;

import org.keycloak.adapters.springboot.*;
import org.keycloak.adapters.springsecurity.*;
import org.keycloak.adapters.springsecurity.authentication.*;
import org.keycloak.adapters.springsecurity.config.*;
import org.keycloak.adapters.springsecurity.management.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.authority.mapping.*;
import org.springframework.security.core.session.*;
import org.springframework.security.web.authentication.session.*;

import static gawlowski.maciej.example.config.ServiceProfile.OTHER_THAN_INTEGRATION_TEST;


@Profile(OTHER_THAN_INTEGRATION_TEST)
@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     * SimpleAuthorityMapper is responsible for automatically adding ROLE_ prefix to any role from Keycloak and convert to upper case (which is required in Spring Security)
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Since Spring 2.1.0, the spring.main.allow-bean-definition-overriding property is set to false by default, differently from the previous versions of Spring.
     * It means that it's not allowed anymore to override a bean already defined.
     */
    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    /**
     * Import configuration from application.yml instead of keycloak.json
     */
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        BaseSecurityConfig.configure(http);
    }
}
