package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Format;
import twentyfive.appcovo2.services.FormatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/formats")
public class FormatController {

    @Autowired
    private FormatService formatService;

    @GetMapping("{id}")
    public ResponseEntity<Format> getFormat(@PathVariable Long id) {
        Optional<Format> format = formatService.getById(id);
        if (format.isPresent()) {
            return ResponseEntity.ok(format.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Format>> getAllFormats() {
        List<Format> format = formatService.getAll();
        return ResponseEntity.ok(format);
    }

    @PostMapping("/save")
    public ResponseEntity<Format> saveFormat(@RequestBody Format format) {
        Format savedFormat = formatService.save(format);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFormat);
    }

    @PutMapping("{id}")
    public ResponseEntity<Format> updateFormat(@PathVariable Long id, @RequestBody Format formatDetails) {
        try{
            Format updatedFormat = formatService.update(id, formatDetails);
            return ResponseEntity.ok(updatedFormat);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFormat(@PathVariable Long id) {
        try {
            formatService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
