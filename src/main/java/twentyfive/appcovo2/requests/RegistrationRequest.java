package twentyfive.appcovo2.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    //private String conferma password no, perchè te la controlli tu sul fe se è uguale
}
