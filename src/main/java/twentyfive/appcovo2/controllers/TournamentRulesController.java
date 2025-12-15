package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.TournamentRules;
import twentyfive.appcovo2.services.TournamentRulesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/rules")
public class TournamentRulesController {

    @Autowired
    private TournamentRulesService tournamentRulesService;

    @GetMapping("{id}")
    public ResponseEntity<TournamentRules> getRule(@PathVariable Long id) {
        Optional<TournamentRules> rule = tournamentRulesService.getById(id);
        if (rule.isPresent()) {
            return ResponseEntity.ok(rule.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TournamentRules>> getAllRules() {
        List<TournamentRules> rules = tournamentRulesService.getAll();
        return ResponseEntity.ok(rules);
    }

    @PostMapping("/save")
    public ResponseEntity<TournamentRules> saveRule(@RequestBody TournamentRules rule) {
        TournamentRules savedRule = tournamentRulesService.save(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRule);
    }

    @PutMapping("{id}")
    public ResponseEntity<TournamentRules> updateRule(@PathVariable Long id, @RequestBody TournamentRules ruleDetails) {
        try{
            TournamentRules updatedRule = tournamentRulesService.update(id, ruleDetails);
            return ResponseEntity.ok(updatedRule);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        try {
            tournamentRulesService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
