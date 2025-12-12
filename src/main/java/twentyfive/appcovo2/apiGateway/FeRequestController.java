package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.requests.RegistrationRequest;

@RestController
@RequestMapping("/openapi")
public class FeRequestController {

    @Autowired
    private FeRequestService feRequestService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.register(registrationRequest);
        return ResponseEntity.ok().build();
    }

}
