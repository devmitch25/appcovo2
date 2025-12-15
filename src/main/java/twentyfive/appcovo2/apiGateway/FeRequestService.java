package twentyfive.appcovo2.apiGateway;

import feign.Response;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.client.KeycloakService;
import twentyfive.appcovo2.models.Player;
import twentyfive.appcovo2.models.Shop;
import twentyfive.appcovo2.requests.LoginRequest;
import twentyfive.appcovo2.requests.RegistrationRequest;
import twentyfive.appcovo2.response.LoginRes;
import twentyfive.appcovo2.services.PlayerService;
import twentyfive.appcovo2.services.ShopService;

import java.util.Collections;

@Service
public class FeRequestService {

    @Autowired
    private KeycloakService keycloakService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ShopService shopService;

    private UserRepresentation createUser(RegistrationRequest request) {
        // 1. CREAZIONE UTENTE SU KEYCLOAK
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        //CHE DELLE VARIABILI SIANO VUOTE O NULLE A KEYCLOAK NON GLIELE FREGA UN CAZZO. OVVIAMENTE NEL DB "" NON VIENE SALVATO COME NULL
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailVerified(true); //TODO

        // Imposta la password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());
        credential.setTemporary(false); // La password non scadrà al primo login
        user.setCredentials(Collections.singletonList(credential));

        return user;
    }

    public void registerPlayer(RegistrationRequest request) {
        UserRepresentation user = createUser(request);

        Response response = keycloakService.addNewUser(user);

        if (response.status() == 201) { //Created
            String [] path = response.headers().get("Location").toArray(new String[0]);
            String keycloakId = path[0].substring(path[0].lastIndexOf("/")+1);

            //Aggiungo ruolo PLAYER
            keycloakService.assignRealmRoleToUser(keycloakId, "PLAYER");

            //Salvo player nel mio DB
            Player newPlayer = new Player();
            newPlayer.setKeycloakId(keycloakId); // Salva l'ID di Keycloak per la sincronizzazione futura
            newPlayer.setEmail(request.getEmail());
            newPlayer.setFirstName(request.getFirstName());
            newPlayer.setLastName(request.getLastName());
            newPlayer.setUsername(request.getUsername());
            newPlayer.setPassword(request.getPassword()); //TODO me la salvo nel mio DB?
            //TODO gestire eventuali doppioni?
            playerService.save(newPlayer); //o chiamo la repository?

        }else {
            String errorBody = "N/A";
            try {
                // Tenta di leggere il corpo della risposta come stringa
                errorBody = response.body().toString();
            } catch (Exception e) {
                // Ignora se la lettura fallisce o il corpo è vuoto
            }
            // Lancia l'eccezione con il messaggio dettagliato
            throw new RuntimeException("Keycloak registration failed: "
                    + response.status() + response.reason()
                    + ". Details: " + errorBody);
        }
    }

    public void registerShop(RegistrationRequest request) {
        UserRepresentation user = createUser(request);

        Response response = keycloakService.addNewUser(user);

        if (response.status() == 201) { //Created
            String [] path = response.headers().get("Location").toArray(new String[0]);
            String keycloakId = path[0].substring(path[0].lastIndexOf("/")+1);

            //Aggiungo ruolo PLAYER
            keycloakService.assignRealmRoleToUser(keycloakId, "SHOP");

            Shop newShop = new Shop();
            newShop.setKeycloakId(keycloakId);

            shopService.save(newShop);

        }else {
            String errorBody = "N/A";
            try {
                // Tenta di leggere il corpo della risposta come stringa
                errorBody = response.body().toString();
            } catch (Exception e) {
                // Ignora se la lettura fallisce o il corpo è vuoto
            }
            // Lancia l'eccezione con il messaggio dettagliato
            throw new RuntimeException("Keycloak registration failed: "
                    + response.status() + response.reason()
                    + ". Details: " + errorBody);
        }
    }

    public LoginRes getTokenLogin(LoginRequest loginRequest) {
        return keycloakService.getTokenLogin(loginRequest);
    }
}
