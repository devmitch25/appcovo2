package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.requests.LoginRequest;
import twentyfive.appcovo2.requests.RegistrationRequest;

@RestController
@RequestMapping("/openapi")
public class FeRequestController {

    @Autowired
    private FeRequestService feRequestService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerPlayer(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.registerPlayer(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody LoginRequest loginRequest) {
        String token = feRequestService.getTokenLogin(loginRequest);
        return new ResponseEntity<String>(token, HttpStatus.FOUND);
    }

    @GetMapping("/prova")
    @PreAuthorize("hasRole('PLAYER')")
    public ResponseEntity<String> getProva() {
        return new ResponseEntity<>("sciao belo", HttpStatus.FOUND);
    }

}
