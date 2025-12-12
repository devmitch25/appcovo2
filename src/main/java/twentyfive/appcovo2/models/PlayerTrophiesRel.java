package twentyfive.appcovo2.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import twentyfive.appcovo2.embeddedKeys.PlayerFormatKeyId;

@Entity
@Data
@NoArgsConstructor
public class PlayerTrophiesRel {

    @EmbeddedId
    private PlayerFormatKeyId playerFormatKeyId;

    @OneToOne  //GIUSTO PER FARE RICERCA VELOCE IN BASE AL GIOCO
    @JoinColumn(name = "game_id")
    private Game game;

    private int trophies;

    public PlayerTrophiesRel(Player player, Format format) {
        this.playerFormatKeyId = new PlayerFormatKeyId(player.getId(), format.getId());
        this.game = format.getGame();
    }

    //TODO SE VIENE ELIMINATO UN GIOCO O UN FORMAT, IL PLAYER PERDE I TROFEI?

}
