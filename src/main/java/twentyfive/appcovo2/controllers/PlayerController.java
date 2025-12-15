package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Player;
import twentyfive.appcovo2.services.PlayerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Optional<Player> player = playerService.getById(id);
        if (player.isPresent()) {
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = playerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    @PostMapping("/save")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        try {
            Player updatedPlayer = playerService.update(id, playerDetails);
            return ResponseEntity.ok(updatedPlayer);
            // return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        try{
            playerService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }






}
