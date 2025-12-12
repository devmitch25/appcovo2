package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import twentyfive.appcovo2.enums.ShippingStatus;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAwardRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_id")
    private Award award;

    private Date request; // year, month, date, hrs, min
    private Date sent; // year, month, date, hrs, min
    private Date toShop; // year, month, date, hrs, min
    private Date toPlayer; // year, month, date, hrs, min

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private ShippingStatus shippingStatus; //TODO DA RIVEDERE


}
