package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Tournament;
import twentyfive.appcovo2.services.TournamentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.getById(id);
        if (tournament.isPresent()) {
            return ResponseEntity.ok(tournament.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAll();
        return ResponseEntity.ok(tournaments);
    }
    //TODO DA RICHIAMARE ANCHE GLI ALTRI ?

    @PostMapping("/save")
    public ResponseEntity<Tournament> saveTournament(@RequestBody Tournament tournament) {
        Tournament savedTournament = tournamentService.save(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTournament);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournamentDetails) {
        try{
            Tournament updatedTournament = tournamentService.update(id, tournamentDetails);
            return ResponseEntity.ok(updatedTournament);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        try {
            tournamentService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
