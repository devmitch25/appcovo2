package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.embeddedKeys.PlayerShopKeyId;
import twentyfive.appcovo2.models.PlayerShopRel;

import java.util.List;

@Repository
public interface PlayerShopRelRepository extends JpaRepository<PlayerShopRel, PlayerShopKeyId> {
    List<PlayerShopRel> findAllByPlayer_Id(Long playerId);

    List<PlayerShopRel> findAllByShop_Id(Long shopId);

    void deleteAllByPlayer_Id(Long playerId);

    void deleteAllByShop_Id(Long shopId);
}
