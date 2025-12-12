package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.embeddedKeys.PlayerFormatKeyId;
import twentyfive.appcovo2.models.PlayerTrophiesRel;
import twentyfive.appcovo2.repositories.PlayerTrophiesRelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerTrophiesRelService {

    @Autowired
    private PlayerTrophiesRelRepository playerTrophiesRelRepository;

    public Optional<PlayerTrophiesRel> getById(PlayerFormatKeyId id) { return playerTrophiesRelRepository.findById(id); }

    public List<PlayerTrophiesRel> getAll(){
        return playerTrophiesRelRepository.findAll();
    }

    public List<PlayerTrophiesRel> getAllByPlayerId(Long playerId) {
        return playerTrophiesRelRepository.findAllByPlayerFormatKeyId_PlayerId(playerId);
    }

    public List<PlayerTrophiesRel> getAllByGameIdAndPlayerId(Long gameId, Long playerId) {
        return playerTrophiesRelRepository.findAllByGame_IdAndPlayerFormatKeyId_PlayerId(gameId, playerId);
    }

    public PlayerTrophiesRel save(PlayerTrophiesRel rel) {
        return playerTrophiesRelRepository.save(rel);
    }
    //TODO L'UPDATE DEI TROFEI SI PUò FARE DIRETTAMENTE CON LA SAVE

    /* //TODO INUTILI?
    public PlayerShopRel update(PlayerShopKeyId id, PlayerShopRel rel) {
        //TODO
        return new PlayerShopRel();
    }

    public PlayerShopRel patch(PlayerShopKeyId id, PlayerShopRel rel) {
        //TODO
        return new PlayerShopRel();
    }
     */

    public void delete(PlayerFormatKeyId id) {
        if (!playerTrophiesRelRepository.existsById(id)) {
            throw new IllegalArgumentException("Relazione con ID: " + id + " non trovato");
        }
        playerTrophiesRelRepository.deleteById(id);
    }

    public void deleteAllByPlayer_Id(Long playerId) {
        if (playerTrophiesRelRepository.findAllByPlayerFormatKeyId_PlayerId(playerId).isEmpty()) {
            throw new IllegalArgumentException("Player con ID: " + playerId + " non trovato");
        }
        playerTrophiesRelRepository.deleteAllByPlayerFormatKeyId_PlayerId(playerId);
    }
}
