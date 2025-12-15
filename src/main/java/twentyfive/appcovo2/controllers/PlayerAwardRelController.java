package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.PlayerAwardRel;
import twentyfive.appcovo2.services.PlayerAwardRelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/cronologiaPremi")
public class PlayerAwardRelController {

    @Autowired
    PlayerAwardRelService playerAwardRelService;

    @GetMapping("{id}")
    public ResponseEntity<PlayerAwardRel> getPlayerAwardRel(@PathVariable Long id) {
        Optional<PlayerAwardRel> rel = playerAwardRelService.getById(id);
        if (rel.isPresent()) {
            return ResponseEntity.ok(rel.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerAwardRel>> getAllPlayerAwardRels() {
        List<PlayerAwardRel> rels = playerAwardRelService.getAll();
        return ResponseEntity.ok(rels);
    }

    @PostMapping("/save")
    public ResponseEntity<PlayerAwardRel> savePlayerAwardRel(@RequestBody PlayerAwardRel rel) {
        PlayerAwardRel savedRel = playerAwardRelService.save(rel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRel);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlayerAwardRel> updatePlayerAwardRel(@PathVariable Long id, @RequestBody PlayerAwardRel relDetails) {
        try{
            PlayerAwardRel updatedRel = playerAwardRelService.update(id, relDetails);
            return ResponseEntity.ok(updatedRel);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayerAwardRel(@PathVariable Long id) {
        try {
            playerAwardRelService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
