package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Game;
import twentyfive.appcovo2.services.GameService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Optional<Game> game = gameService.getById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAll();
        return ResponseEntity.ok(games);
    }

    @PostMapping("/save")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        Game savedGame = gameService.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @PutMapping("{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {
        try{
            Game updatedGame = gameService.update(id, gameDetails);
            return ResponseEntity.ok(updatedGame);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            gameService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
