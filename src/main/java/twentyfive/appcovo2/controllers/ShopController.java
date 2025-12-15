package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Shop;
import twentyfive.appcovo2.services.ShopService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("{id}")
    public ResponseEntity<Shop> getShop(@PathVariable Long id) {
        Optional<Shop> shop = shopService.getById(id);
        if (shop.isPresent()) {
            return ResponseEntity.ok(shop.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopService.getAll();
        return ResponseEntity.ok(shops);
    }

    @PostMapping("/save")
    public ResponseEntity<Shop> saveShop(@RequestBody Shop shop) {
        Shop savedShop = shopService.save(shop);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShop);
    }

    @PutMapping("{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @RequestBody Shop shopDetails) {
        try{
            Shop updatedShop = shopService.update(id, shopDetails);
            return ResponseEntity.ok(updatedShop);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        try {
            shopService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
