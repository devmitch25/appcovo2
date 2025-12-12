package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "keycloak_id", unique = true, nullable = false)
    private String keycloakId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    private String password;
    private String image;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    private Shop theBestShop;

    private int coins; //TODO come cazzo li ottiene i coins? BOOOOOH


}
