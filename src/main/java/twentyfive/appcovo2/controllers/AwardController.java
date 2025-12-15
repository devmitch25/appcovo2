package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Award;
import twentyfive.appcovo2.services.AwardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/awards")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @GetMapping("{id}")
    public ResponseEntity<Award> getAward(@PathVariable Long id) {
        Optional<Award> award = awardService.getById(id);
        if (award.isPresent()) {
            return ResponseEntity.ok(award.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Award>> getAllAwards() {
        List<Award> awards = awardService.getAll();
        return ResponseEntity.ok(awards);
    }

    @PostMapping("/save")
    public ResponseEntity<Award> saveAward(@RequestBody Award award) {
        Award savedAward = awardService.save(award);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAward);
    }

    @PutMapping("{id}")
    public ResponseEntity<Award> updateAward(@PathVariable Long id, @RequestBody Award awardDetails) {
        try{
            Award updatedAward = awardService.update(id, awardDetails);
            return ResponseEntity.ok(updatedAward);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable Long id) {
        try {
            awardService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

}
