package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.Game;
import twentyfive.appcovo2.repositories.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Optional<Game> getById(Long id) {
        return gameRepository.findById(id);
    }

    public Optional<Game> getByName(String name) {
        return gameRepository.findByName(name);
    }

    public List<Game> getAll(){
        return gameRepository.findAll();
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public Game update(Long id, Game game) {
        //TODO
        return new Game();
    }

    public Game patch(Long id, Game game) {
        //TODO
        return new Game();
    }

    public void delete(Long id) {
        if(!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Game con ID: "+id+" non trovato");
        }
        gameRepository.deleteById(id);
    }
}
