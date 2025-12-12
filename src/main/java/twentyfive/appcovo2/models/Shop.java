package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "keycloak_id", unique = true, nullable = false)
    private String keycloakId;

    private final String name = "Covo del Nerd"; //TODO giusto?
    private String city; //TODO vincolo UNIQUE o la facciamo diventare entità con rel OneToOne?
    private String address;
    private String linkGoogleMaps;
    @Column(unique = true)
    private String phoneNumber;
    private String linkFacebook;
    private String linkInstagram;
    private String linkTikTok;
    private String image;

    /* NON SERVE PERCHè IO MI SALVO LO STORICO PLAYER-AWARD e posso interrogare il database prendendo il theBestShop dal player per gli award che devo ricevere o consegnare.
    @OneToMany
    private List<Award> awards; //che devono arrivare dalla sede principale e che devo dare al player

     */

}
