package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.embeddedKeys.PlayerShopKeyId;
import twentyfive.appcovo2.models.PlayerShopRel;
import twentyfive.appcovo2.repositories.PlayerShopRelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerShopRelService {

    @Autowired
    private PlayerShopRelRepository playerShopRelRepository;

    public Optional<PlayerShopRel> getById(PlayerShopKeyId id) { return playerShopRelRepository.findById(id); }

    public List<PlayerShopRel> getAll(){
        return playerShopRelRepository.findAll();
    }

    public List<PlayerShopRel> getAllByPlayerId(Long playerId) {
        return playerShopRelRepository.findAllByPlayer_Id(playerId);
    }

    public List<PlayerShopRel> getAllByShopId(Long shopId) {
        return playerShopRelRepository.findAllByShop_Id(shopId);
    }

    public PlayerShopRel save(PlayerShopRel rel) {
        return playerShopRelRepository.save(rel);
    }


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

    public void delete(PlayerShopKeyId id) {
        if (!playerShopRelRepository.existsById(id)) {
            throw new IllegalArgumentException("Relazione con ID: " + id + " non trovato");
        }
        playerShopRelRepository.deleteById(id);
    }

    public void deleteAllByPlayer_Id(Long playerId) {
        if (playerShopRelRepository.findAllByPlayer_Id(playerId).isEmpty()) {
            throw new IllegalArgumentException("Player con ID: " + playerId + " non trovato");
        }
        playerShopRelRepository.deleteAllByPlayer_Id(playerId);
    }

    public void deleteAllByShop_Id(Long shopId) {
        if (playerShopRelRepository.findAllByPlayer_Id(shopId).isEmpty()) {
            throw new IllegalArgumentException("Shop con ID: " + shopId + " non trovato");
        }
        playerShopRelRepository.deleteAllByShop_Id(shopId);
    }

}
