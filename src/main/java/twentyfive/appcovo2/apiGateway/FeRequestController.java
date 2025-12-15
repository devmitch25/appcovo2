package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.requests.LoginRequest;
import twentyfive.appcovo2.requests.RegistrationRequest;

@RestController
@RequestMapping("/api")
public class FeRequestController {

    @Autowired
    private FeRequestService feRequestService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerPlayer(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.registerPlayer(registrationRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody LoginRequest loginRequest) {
        String token = feRequestService.getTokenLogin(loginRequest);
        return ResponseEntity.ok(token);
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

}
