package twentyfive.appcovo2.embeddedKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerShopKeyId implements Serializable {

    private Long playerId;
    private Long shopId;

}
