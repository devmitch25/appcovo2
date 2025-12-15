package twentyfive.appcovo2.client;

import feign.Response;
import org.hibernate.internal.util.StringHelper;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.exceptions.TokenRetrievalException;
import twentyfive.appcovo2.facade.KeycloakFacade;
import twentyfive.appcovo2.models.Admin;
import twentyfive.appcovo2.models.Player;
import twentyfive.appcovo2.models.Shop;
import twentyfive.appcovo2.requests.LoginRequest;
import twentyfive.appcovo2.requests.TokenRequest;
import twentyfive.appcovo2.response.LoginRes;
import twentyfive.appcovo2.services.AdminService;
import twentyfive.appcovo2.services.PlayerService;
import twentyfive.appcovo2.services.ShopService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KeycloakService {

    @Autowired
    private KeycloakFacade keycloak;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private AdminService adminService;


    public String getAdminBearerToken() {
        LoginRes res = keycloak.getAdminBearerToken();
        String access = res != null ? res.getAccessToken() : null;
        if (access == null || access.isBlank()) {
            throw new TokenRetrievalException("Unable to retrieve admin access token from Keycloak");
        }
        return "Bearer " + access;
    }

    public Response addNewUser(UserRepresentation user) {
        String accessToken = getAdminBearerToken();
        return keycloak.addUser(accessToken, user);
    }

    public Set<String> getAllRealmRoleNames() {
        String adminToken = getAdminBearerToken();
        List<RoleRepresentation> roles = keycloak.getAllRealmRoles(adminToken);

        // 3. Converte gli oggetti RoleRepresentation in semplici nomi (String)
        return roles.stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toSet());
    }

    public void assignRealmRoleToUser(String userId, String roleName) {
        String adminToken = getAdminBearerToken();
        RoleRepresentation roleObject = keycloak.getRealmRoleByName(adminToken, roleName );
        List<RoleRepresentation> rolesToAssign = Collections.singletonList(roleObject);
        keycloak.addRealmRolesToUser(adminToken, userId, rolesToAssign);
    }


    public String getTokenLogin(LoginRequest loginRequest) {
        LoginRes res = keycloak.getToken(loginRequest.getUsername(), loginRequest.getPassword());
        String access = res != null ? res.getAccessToken() : null;
        if (access == null || access.isBlank()) {
            throw new TokenRetrievalException("Unable to retrieve admin access token from Keycloak");
        }
        return "Bearer " + access;
    }

    public void resetPasswordFromEmail(String email) {
        try {
            String kcId=null;
            Optional<Player> player= playerService.getByEmail(email);
            if (player.isPresent()) {
                kcId=player.get().getKeycloakId();
            } else {
                Optional<Shop> shop= shopService.getByEmail(email);
                if (shop.isPresent()) {
                    kcId=shop.get().getKeycloakId();
                }else {
                    Optional<Admin> admin= adminService.getByEmail(email);
                    if (admin.isPresent()) {
                        kcId=admin.get().getKeycloakId();
                    }
                }
            }
            if (kcId==null) throw new Exception();

            List<String> actions = Collections.singletonList("UPDATE_PASSWORD");
            keycloak.executeAction(getAdminBearerToken(), kcId, actions);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send reset password request. User not found with email: " + email, e);
        }
    }

}
