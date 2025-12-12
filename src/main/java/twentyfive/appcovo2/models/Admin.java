package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keycloak_id", unique = true, nullable = false)
    private String keycloakId;

    /* SERVONO?
    private String firstName;
    private String lastName;
     */

    @Column(unique = true)
    private String email;
    private String password;
    private String image;
    @Column(unique = true)
    private String phoneNumber;

}
