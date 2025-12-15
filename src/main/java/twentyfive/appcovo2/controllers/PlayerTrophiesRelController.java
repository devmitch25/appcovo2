package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.embeddedKeys.PlayerFormatKeyId;
import twentyfive.appcovo2.models.PlayerTrophiesRel;
import twentyfive.appcovo2.services.PlayerTrophiesRelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/trofei")
public class PlayerTrophiesRelController {

    @Autowired
    private PlayerTrophiesRelService playerTrophiesRelService;

    @GetMapping("/{pId}/{fId}")
    public ResponseEntity<PlayerTrophiesRel> getPlayerTrophiesRel(@PathVariable Long pId, @PathVariable Long fId) {
        PlayerFormatKeyId id = new PlayerFormatKeyId(pId, fId);
        Optional<PlayerTrophiesRel> rel = playerTrophiesRelService.getById(id);
        if (rel.isPresent()) {
            return ResponseEntity.ok(rel.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerTrophiesRel>> getAllPlayerTrophiesRels() {
        List<PlayerTrophiesRel> rels = playerTrophiesRelService.getAll();
        return ResponseEntity.ok(rels);
    }

    @GetMapping("/allByPlayer")
    public ResponseEntity<List<PlayerTrophiesRel>> getAllPlayerTrophiesRelsByPlayer_Id(@RequestParam Long playerId) {
        List<PlayerTrophiesRel> rels = playerTrophiesRelService.getAllByPlayerId(playerId);
        return ResponseEntity.ok(rels);
    }

    @GetMapping("/allByGameAndPlayer")
    public ResponseEntity<List<PlayerTrophiesRel>> getAllPlayerTrophiesRelsByGameIdAndPlayerId(@RequestParam Long gameId, @RequestParam Long playerId) {
        List<PlayerTrophiesRel> rels = playerTrophiesRelService.getAllByGameIdAndPlayerId(gameId, playerId);
        return ResponseEntity.ok(rels);
    }


    @PostMapping("/save")
    public ResponseEntity<PlayerTrophiesRel> savePlayerTrophiesRel(@RequestBody PlayerTrophiesRel rel) {
        PlayerTrophiesRel savedRel = playerTrophiesRelService.save(rel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRel);
    }


    /* //TODO LA PUT LA FACCIO CON LA SAVE, NO? VANNO AGGIORNATI I TROFEI
    @PutMapping("/{pId}/{sId}")
    public ResponseEntity<PlayerShopRel> updatePlayerAwardRel(@PathVariable Long pId, @PathVariable Long sId, @RequestBody PlayerShopRel relDetails) {
        try{
            PlayerShopKeyId id = new PlayerShopKeyId(pId, sId);
            PlayerShopRel updatedRel = playerShopRelService.update(id, relDetails);
            return ResponseEntity.ok(updatedRel);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
     */

    @DeleteMapping("/{pId}/{fId}")
    public ResponseEntity<Void> deletePlayerShopRel(@PathVariable Long pId, @PathVariable Long fId) {
        try {
            PlayerFormatKeyId id = new PlayerFormatKeyId(pId, fId);
            playerTrophiesRelService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllPlayerShopRelByPlayer_Id(@RequestParam(value = "playerId") Long playerId) {
        try {
            playerTrophiesRelService.deleteAllByPlayer_Id(playerId);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

}
