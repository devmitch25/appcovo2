package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twentyfive.appcovo2.models.Shop;
import twentyfive.appcovo2.requests.RegistrationRequest;

@RestController
@RequestMapping("/api/admin")
public class FeAdminReqController {

    @Autowired
    private FeRequestService feRequestService;

    @PostMapping("/saveShop")
    public ResponseEntity saveShop(@RequestBody RegistrationRequest registrationRequest) {
        feRequestService.registerShop(registrationRequest);
        return ResponseEntity.ok().build();
    }

}
