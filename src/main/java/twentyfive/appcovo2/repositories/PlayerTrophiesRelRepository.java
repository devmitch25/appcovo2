package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.embeddedKeys.PlayerFormatKeyId;
import twentyfive.appcovo2.models.PlayerTrophiesRel;

import java.util.List;

@Repository
public interface PlayerTrophiesRelRepository extends JpaRepository<PlayerTrophiesRel, PlayerFormatKeyId> {
    List<PlayerTrophiesRel> findAllByPlayerFormatKeyId_PlayerId(Long playerFormatKeyIdPlayerId);

    List<PlayerTrophiesRel> findAllByGame_IdAndPlayerFormatKeyId_PlayerId(Long gameId, Long playerFormatKeyIdPlayerId);

    void deleteAllByPlayerFormatKeyId_PlayerId(Long playerFormatKeyIdPlayerId);
}
