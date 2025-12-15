package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.client.KeycloakService;
import twentyfive.appcovo2.dtos.BaseController;
import twentyfive.appcovo2.requests.LoginRequest;
import twentyfive.appcovo2.requests.RegistrationRequest;
import twentyfive.appcovo2.response.LoginRes;
import twentyfive.appcovo2.response.ResponseWrapper;

@RestController
@RequestMapping("/keycloak")
public class FeRequestController extends BaseController {

    @Autowired
    private FeRequestService feRequestService;
    @Autowired
    private KeycloakService keycloakService;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<Void>> registerPlayer(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.registerPlayer(registrationRequest);
        return created("User Created Succesfully");
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginRes>> getToken(@RequestBody LoginRequest loginRequest) {
        return ok(feRequestService.getTokenLogin(loginRequest), "Login successful");
    }


    @GetMapping("/egg")
    public ResponseEntity<String> getEgg() {
        return new ResponseEntity<>("sciao belo", HttpStatus.FOUND);
    }

    @GetMapping("/prova")
    @PreAuthorize("hasRole('PLAYER')")
    public ResponseEntity<String> getProva() {
        return new ResponseEntity<>("sciao belo", HttpStatus.FOUND);
    }

    @PostMapping("/saveShop")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> saveShop(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.registerShop(registrationRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Object> resetPasswordFromEmail(@RequestParam String email){
        try {
            keycloakService.resetPasswordFromEmail(email);
            return ResponseEntity.ok("Email Reset Password Sent!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send reset password email for: "+email);
        }
    }

}
