package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.Player;
import twentyfive.appcovo2.repositories.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Player> getById(Long id) {
        return playerRepository.findById(id);
    }

    public Optional<Player> getByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Optional<Player> getByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public Player save(Player player) {
        //TODO gestire il fatto che se il player esiste già va annullato tutto
        // o lascio perdere e in caso viene effettuato l'update automatico dei campi?
        return playerRepository.save(player);
    }

    public Player update(Long id, Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player non trovato con ID: " + id));
        existingPlayer.setFirstName(updatedPlayer.getFirstName());
        existingPlayer.setLastName(updatedPlayer.getLastName());
        existingPlayer.setUsername(updatedPlayer.getUsername());
        existingPlayer.setEmail(updatedPlayer.getEmail());
        // TODO TO BE CONTINUED SE CORRETTO

        return playerRepository.save(existingPlayer);
    }

    //TODO DA FARE PATCH?

    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player non trovato con ID: " + id);
        }
        playerRepository.deleteById(id);
        //TODO QUANDO VIENE CANCELLATO, VA ELIMINATO ANCHE DAL RESTO DELLE TABELLE RELAZIONALI
        //TODO QUALI PLAYER-SHOP, PLAYER-AWARD, PLAYER-TROPHIES
    }

}
