package twentyfive.appcovo2.client;

import org.keycloak.representations.idm.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.requests.TokenRequest;
import twentyfive.appcovo2.response.LoginRes;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient(name = "keycloak-admin-api", url = "${keycloak.admin.server-url}")
public interface KeycloakApi {

    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    LoginRes getToken(@RequestBody TokenRequest params, @PathVariable("realm") String realm);

    @PostMapping(value = "/admin/realms/{realm}/users", produces = "application/json")
    feign.Response add(@RequestHeader("Authorization") String accessToken,
                       @PathVariable("realm") String realm,
                       @RequestBody UserRepresentation user);

    @GetMapping(value = "/admin/realms/{realm}/roles", produces = "application/json")
    List<RoleRepresentation> getAllRealmRoles(
            @RequestHeader("Authorization") String accessToken, // Bearer Token
            @PathVariable("realm") String realm
    );

    @GetMapping(value = "/admin/realms/{realm}/roles/{roleName}", produces = "application/json")
    RoleRepresentation getRealmRoleByName(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("realm") String realm,
            @PathVariable("roleName") String roleName
    );

    @PostMapping(value = "/admin/realms/{realm}/users/{userId}/role-mappings/realm", consumes = "application/json")
    void addRealmRolesToUser(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("realm") String realm,
            @PathVariable("userId") String userId,
            @RequestBody List<RoleRepresentation> roles
    );



    /*

    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    LoginRes getRefreshToken(@RequestBody RefreshLoginReq params, @PathVariable("realm") String realm);

    @GetMapping(value = "/admin/realms/{realm}/users/{id}")
    UserRepresentation getUserByKeycloakId(@RequestHeader("Authorization") String accessToken,
                                           @PathVariable("realm") String realm,
                                           @PathVariable("id") String id);

    @GetMapping(value = "/admin/realms/{realm}/users", params = "username")
    List<KeycloakUser> getUserByUsername(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("realm") String realm,
                                         @RequestParam("username") String username);

    @GetMapping(value = "/admin/realms/{realm}/users/{userId}/sessions")
    List<UserSessionRepresentation> getSession(@RequestHeader("Authorization") String accessToken,
                                               @PathVariable("realm") String realm,
                                               @PathVariable("userId") String userId);

    @DeleteMapping(value = "/admin/realms/{realm}/sessions/{sessionId}")
    ResponseEntity<Void> deleteSession(@RequestHeader("Authorization") String accessToken,
                                       @PathVariable("realm") String realm,
                                       @PathVariable("sessionId") String sessionId);


    @PutMapping(value = "/admin/realms/{realm}/users/{id}")
    ResponseEntity<UserRepresentation> update(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("realm") String realm,
                                              @PathVariable("id") String id,
                                              @RequestBody UserRepresentation user);

    @PutMapping(value = "/admin/realms/{realm}/users/{userId}/execute-actions-email",
            produces = "application/json")
    ResponseEntity<Object> executeAction(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("realm") String realm,
                                         @PathVariable("userId") String userId,
                                         @RequestBody List<String> actions,
                                         @RequestParam("redirect_uri") String redirectUri,
                                         @RequestParam("client_id") String clientId
                                         );

    @PutMapping(value = "/admin/realms/{realm}/users/{userId}/reset-password",
            produces = "application/json")
    ResponseEntity<Object> updatePassword(@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("realm") String realm,
                                          @PathVariable("userId") String userId,
                                          @RequestBody CredentialRepresentation credential,
                                          @RequestParam("redirect_uri") String redirectUri,
                                          @RequestParam("client_id") String clientId);

    @GetMapping(value = "/admin/realms/{realm}/roles")
    List<LinkedHashMap<String, String>> getRoles(@RequestHeader("Authorization") String accessToken,
                                                 @PathVariable("realm") String realm);

    @PostMapping(value = "/admin/realms/{realm}/users/{id}/role-mappings/realm",
            produces = "application/json")
    ResponseEntity<Object> addRoleToUser(@RequestHeader("Authorization") String accessToken,
                                         @PathVariable("realm") String realm,
                                         @PathVariable("id") String id,
                                         @RequestBody List<RoleRepresentation> roles);

    @DeleteMapping(value = "/admin/realms/{realm}/users/{id}/role-mappings/realm",
            produces = "application/json")
    ResponseEntity<Object> removeRoleFromUser(@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("realm") String realm,
                                              @PathVariable("id") String id,
                                              @RequestBody List<RoleRepresentation> roles);

    @PostMapping(value = "/admin/realms/{realm}/roles", produces = "application/json")
    void addRealmRole(@RequestHeader("Authorization") String accessToken,
                      @PathVariable("realm") String realm,
                      @RequestBody AddRealmRoleReq request);

    @DeleteMapping(value = "/admin/realms/{realm}/users/{id}")
    ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String accessToken,
                                    @PathVariable("realm") String realm,
                                    @PathVariable("id") String id);

    @PostMapping(value = "/admin/realms/{realm}/users/{userId}/logout")
    ResponseEntity<Void> logoutUser(@RequestHeader("Authorization") String accessToken,
                                    @PathVariable("realm") String realm,
                                    @PathVariable("userId") String userId);


     */
}
