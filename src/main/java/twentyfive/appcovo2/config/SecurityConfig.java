package twentyfive.appcovo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Abilita la sicurezza basata su annotazioni (@PreAuthorize)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF (tipico per API stateless)
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/keycloak/register", "/keycloak/login", "/keycloak/reset-password").permitAll()

                        .requestMatchers("/test/**").denyAll()
                        // Tutte le altre richieste richiedono l'autenticazione OAuth2
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    /**
     * Converter personalizzato per estrarre i ruoli da Keycloak e
     * mapparli come autorità in Spring Security.
     * * Keycloak di solito inserisce i ruoli del realm in un claim 'realm_access.roles'.
     */
    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return jwt -> {
            // Estrae il claim 'realm_access'
            Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

            if (realmAccess == null || !realmAccess.containsKey("roles")) {
                return List.of();
            }

            // Estrae la lista dei ruoli e li mappa in SimpleGrantedAuthority
            Collection<String> roles = (Collection<String>) realmAccess.get("roles");

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .collect(Collectors.toList());
        };
    }

    /**
     * Configura il JwtAuthenticationConverter per utilizzare il converter dei ruoli.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }
}
