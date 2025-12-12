package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.embeddedKeys.PlayerShopKeyId;
import twentyfive.appcovo2.models.PlayerShopRel;
import twentyfive.appcovo2.services.PlayerShopRelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/follow")
public class PlayerShopRelController {

    @Autowired
    private PlayerShopRelService playerShopRelService;


    @GetMapping("/{pId}/{sId}")
    public ResponseEntity<PlayerShopRel> getPlayerShopRel(@PathVariable Long pId, @PathVariable Long sId) {
        PlayerShopKeyId id = new PlayerShopKeyId(pId, sId);
        Optional<PlayerShopRel> rel = playerShopRelService.getById(id);
        if (rel.isPresent()) {
            return ResponseEntity.ok(rel.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerShopRel>> getAllPlayerShopRels(
            @RequestParam(value = "playerId", required = false) Long playerId,
            @RequestParam(value = "shopId", required = false) Long shopId
    ) {
        if (playerId != null && shopId != null) {
            List<PlayerShopRel> rels = new ArrayList<>();
            rels.add(getPlayerShopRel(playerId, shopId).getBody());
            return ResponseEntity.ok(rels);
        }
        else if (playerId != null && shopId == null) {
            List<PlayerShopRel> rels = playerShopRelService.getAllByPlayerId(playerId);
            return ResponseEntity.ok(rels);
        }
        else if (playerId == null && shopId != null) {
            List<PlayerShopRel> rels = playerShopRelService.getAllByPlayerId(playerId);
            return ResponseEntity.ok(rels);
        }
        return ResponseEntity.ok(playerShopRelService.getAll());
    }

    /*
    @GetMapping
    public ResponseEntity<List<PlayerShopRel>> getAllPlayerShopRelsByPlayer_Id(@RequestParam Long playerId) {
        List<PlayerShopRel> rels = playerShopRelService.getAllByPlayerId(playerId);
        return ResponseEntity.ok(rels);
    }

    @GetMapping
    public ResponseEntity<List<PlayerShopRel>> getAllPlayerShopRelsByShop_Id(@RequestParam Long shopId) {
        List<PlayerShopRel> rels = playerShopRelService.getAllByPlayerId(playerId);
        return ResponseEntity.ok(rels);
    }
     */



    @PostMapping("/save")
    public ResponseEntity<PlayerShopRel> savePlayerAwardRel(@RequestBody PlayerShopRel rel) {
        PlayerShopRel savedRel = playerShopRelService.save(rel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRel);
    }

    /* //TODO PENSO SIA INUTILE L'UPDATE IN QUESTA RELAZIONE
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

    @DeleteMapping("/{pId}/{sId}")
    public ResponseEntity<Void> deletePlayerShopRel(@PathVariable Long pId, @PathVariable Long sId) {
        try {
            PlayerShopKeyId id = new PlayerShopKeyId(pId, sId);
            playerShopRelService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlayerShopRelBy(
            @RequestParam(value = "playerId", required = false) Long playerId,
            @RequestParam(value = "shopId", required = false) Long shopId) {

        if (playerId != null && shopId == null) {
            try {
                playerShopRelService.deleteAllByPlayer_Id(playerId);
                return ResponseEntity.noContent().build();
            }catch (IllegalArgumentException e){
                return ResponseEntity.notFound().build();
            }

        } else if (shopId != null && playerId == null) {
            try {
                playerShopRelService.deleteAllByShop_Id(shopId);
                return ResponseEntity.noContent().build();
            }catch (IllegalArgumentException e){
                return ResponseEntity.notFound().build();
            }

        } else {
            // Errore: Nessun ID o troppi ID specificati
            throw new IllegalArgumentException("Specificare esattamente uno tra 'playerId' o 'shopId' per l'eliminazione.");
        }
    }

    /*
    @DeleteMapping
    public ResponseEntity<Void> deleteAllPlayerShopRelByPlayer_Id(@RequestParam(value = "playerId") Long playerId) {
        try {
            playerShopRelService.deleteAllByPlayer_Id(playerId);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPlayerShopRelByShop_Id(@RequestParam(value = "shopId") Long shopId) {
        try {
            playerShopRelService.deleteAllByShop_Id(shopId);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
     */
}
