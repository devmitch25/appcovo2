package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twentyfive.appcovo2.models.Format;
import twentyfive.appcovo2.models.Game;
import twentyfive.appcovo2.repositories.FormatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FormatService {

    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private GameService gameService;

    public Optional<Format> getByNameAndGameId(String name, Long gameId) {
        return formatRepository.findByNameAndGame_Id(name, gameId);
    }

    public Optional<Format> getById(Long id) {
        return formatRepository.findById(id);
    }

    public List<Format> getAll(){
        return formatRepository.findAll();
    }

    public Format save(Format format) {
        return formatRepository.save(format);
    }

    public Format update(Long id, Format format) {
        //TODO
        return new Format();
    }

    public Format patch(Long id, Format format) {
        //TODO
        return new Format();
    }

    @Transactional
    public void delete(Long id) {
        if(!formatRepository.existsById(id)) {
            throw new IllegalArgumentException("Format con ID: "+id+" non trovato");
        }
        Format format = formatRepository.findById(id).orElse(null);
        Game game = format.getGame();
        List<Format> gameFormats = game.getFormats();
        gameFormats.remove(format);
        game.setFormats(gameFormats);
        gameService.save(game);
        formatRepository.deleteById(id);
    }
}
