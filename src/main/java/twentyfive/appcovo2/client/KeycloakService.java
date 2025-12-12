package twentyfive.appcovo2.client;

import feign.Response;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.exceptions.TokenRetrievalException;
import twentyfive.appcovo2.requests.TokenRequest;
import twentyfive.appcovo2.response.LoginRes;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KeycloakService {

    @Autowired
    private KeycloakApi keycloakApi;

    @Value("${keycloak.admin.realm-name}")
    private String realm;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String client_id;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String client_secret;
    @Value("${spring.security.oauth2.client.registration.keycloak.authorization-grant-type}")
    private String grant_type;
    @Value("${keycloak.admin.username}")
    private String username;
    @Value("${keycloak.admin.password}")
    private String password;

    public String getAdminBearerToken() {
        TokenRequest tokenRequest = new TokenRequest(client_id, client_secret, grant_type, username, password);
        LoginRes res = keycloakApi.getToken(tokenRequest, realm);
        String access = res != null ? res.getAccessToken() : null;
        if (access == null || access.isBlank()) {
            throw new TokenRetrievalException("Unable to retrieve admin access token from Keycloak");
        }

        return "Bearer " + access;
    }

    public Response addNewUser(UserRepresentation user) {
        String accessToken = getAdminBearerToken();
        return keycloakApi.add(accessToken, realm, user);
    }

    public Set<String> getAllRealmRoleNames() {
        // 1. Ottiene il token admin
        String adminToken = getAdminBearerToken();

        // 2. Chiama l'API per tutti i ruoli
        List<RoleRepresentation> roles = keycloakApi.getAllRealmRoles(
                adminToken,
                this.realm // Il realm iniettato tramite @Value
        );

        // 3. Converte gli oggetti RoleRepresentation in semplici nomi (String)
        return roles.stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toSet());
    }

    public void assignRealmRoleToUser(String userId, String roleName) {
        // 1. Ottiene il token admin
        String adminToken = getAdminBearerToken();

        // 2. Recupera l'oggetto RoleRepresentation per il nome
        RoleRepresentation roleObject = keycloakApi.getRealmRoleByName(
                adminToken,
                this.realm,
                roleName
        );

        // 3. Esegue la POST con la lista del ruolo
        List<RoleRepresentation> rolesToAssign = Collections.singletonList(roleObject);

        keycloakApi.addRealmRolesToUser(
                adminToken,
                this.realm,
                userId,
                rolesToAssign
        );
    }



}
