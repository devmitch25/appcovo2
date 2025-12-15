package twentyfive.appcovo2.facade;

import feign.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.client.KeycloakApi;
import twentyfive.appcovo2.requests.TokenRequest;
import twentyfive.appcovo2.response.LoginRes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakFacade {

    @Value("")
    private String redirectUri="placeorder";

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

    @Autowired
    private KeycloakApi api;

    /** Garantisce il prefisso "Bearer " se manca */
    private String bearer(String tokenOrBearer) {
        if (tokenOrBearer == null || tokenOrBearer.isBlank()) return tokenOrBearer;
        return tokenOrBearer.startsWith("Bearer ") ? tokenOrBearer : "Bearer " + tokenOrBearer;
    }

    public LoginRes getAdminBearerToken(){
        TokenRequest tokenRequest = new TokenRequest(client_id, client_secret, grant_type, username, password);
        return api.getToken(tokenRequest, realm);
    }

    public Response addUser(String accessToken, UserRepresentation user) {
        return api.add(accessToken, realm, user);
    }


    public List<RoleRepresentation> getAllRealmRoles(String adminToken) {
        return api.getAllRealmRoles(adminToken, realm);
    }


    public RoleRepresentation getRealmRoleByName(String adminToken, String roleName) {
        return api.getRealmRoleByName(adminToken, realm, roleName);
    }

    public void addRealmRolesToUser(String adminToken, String userId, List<RoleRepresentation> rolesToAssign) {
        api.addRealmRolesToUser(adminToken, realm, userId, rolesToAssign);
    }

    public LoginRes getToken(String user, String pass) {
        TokenRequest tokenRequest = new TokenRequest(client_id, client_secret, grant_type, user, pass);
        return api.getToken(tokenRequest, realm);
    }

    public void executeAction(String accessToken, String keycloackId, List<String> actions) {
        api.executeAction(accessToken, realm, keycloackId, actions, redirectUri, client_id);
    }
}
