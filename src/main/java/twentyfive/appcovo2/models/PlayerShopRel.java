package twentyfive.appcovo2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import twentyfive.appcovo2.embeddedKeys.PlayerShopKeyId;

@Entity
@Data
@NoArgsConstructor
public class PlayerShopRel {

    @EmbeddedId
    private PlayerShopKeyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shopId")
    @JoinColumn(name = "shop_id")
    private Shop shop;

    /* VA MESSO? */
    public PlayerShopRel(Player player, Shop shop) {
        this.id=new PlayerShopKeyId(player.getId(), shop.getId());
        this.player=player;
        this.shop=shop;
    }

}

