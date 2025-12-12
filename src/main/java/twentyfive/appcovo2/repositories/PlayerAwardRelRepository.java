package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.enums.ShippingStatus;
import twentyfive.appcovo2.models.PlayerAwardRel;

import java.util.List;

@Repository
public interface PlayerAwardRelRepository extends JpaRepository<PlayerAwardRel, Long> {

    List<PlayerAwardRel> findAllByShippingStatus(ShippingStatus shippingStatus);

    List<PlayerAwardRel> findAllByPlayer_Id(Long playerId);

    List<PlayerAwardRel> findAllByAward_Id(Long awardId);

    void deleteAllByPlayer_Id(Long playerId);

    void deleteAllByAward_Id(Long awardId);
}
