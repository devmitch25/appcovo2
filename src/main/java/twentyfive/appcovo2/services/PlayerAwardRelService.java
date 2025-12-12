package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.enums.ShippingStatus;
import twentyfive.appcovo2.models.PlayerAwardRel;
import twentyfive.appcovo2.repositories.PlayerAwardRelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerAwardRelService {

    @Autowired
    PlayerAwardRelRepository playerAwardRelRepository;

    public Optional<PlayerAwardRel> getById(Long id) {
        return playerAwardRelRepository.findById(id);
    }

    public List<PlayerAwardRel> getAll(){
        return playerAwardRelRepository.findAll();
    }

    public List<PlayerAwardRel> getAllByShippingStatus(ShippingStatus shippingStatus) {
        return playerAwardRelRepository.findAllByShippingStatus(shippingStatus);
    }

    public List<PlayerAwardRel> getAllByPlayerId(Long playerId) {
        return playerAwardRelRepository.findAllByPlayer_Id(playerId);
    }

    public List<PlayerAwardRel> getAllByAwardId(Long awardId) {
        return playerAwardRelRepository.findAllByAward_Id(awardId);
    }

    public PlayerAwardRel save(PlayerAwardRel award) {
        return playerAwardRelRepository.save(award);
    }

    public PlayerAwardRel update(Long id, PlayerAwardRel award) {
        //TODO
        return new PlayerAwardRel();
    }

    public PlayerAwardRel patch(Long id, PlayerAwardRel award) {
        //TODO
        return new PlayerAwardRel();
    }

    public void delete(Long id) {
        if (!playerAwardRelRepository.existsById(id)) {
            throw new IllegalArgumentException("Relazione con ID: " + id + " non trovato");
        }
        playerAwardRelRepository.deleteById(id);
    }

    public void deleteAllByPlayer_Id(Long playerId) {
        if (playerAwardRelRepository.findAllByPlayer_Id(playerId).isEmpty()) {
            throw new IllegalArgumentException("Player con ID: " + playerId + " non trovato");
        }
        playerAwardRelRepository.deleteAllByPlayer_Id(playerId);
    }

    public void deleteAllByAward_Id(Long awardId) {
        if (playerAwardRelRepository.findAllByPlayer_Id(awardId).isEmpty()) {
            throw new IllegalArgumentException("Award con ID: " + awardId + " non trovato");
        }
        playerAwardRelRepository.deleteAllByAward_Id(awardId);
    }


}
