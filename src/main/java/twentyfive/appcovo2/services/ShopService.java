package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.Shop;
import twentyfive.appcovo2.repositories.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Optional<Shop> getById(Long id) {
        return shopRepository.findById(id);
    }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop update(Long id, Shop shop) {
        //TODO
        return new Shop();
    }

    public Shop patch(Long id, Shop shop) {
        //TODO
        return new Shop();
    }

    public void delete(Long id) {
        if(!shopRepository.existsById(id)) {
            throw new IllegalArgumentException("Shop con ID: "+id+" non trovato");
        }
        shopRepository.deleteById(id);
        //TODO QUANDO VIENE CANCELLATO, VA ELIMINATO ANCHE DAL RESTO DELLE TABELLE RELAZIONALI
        //TODO QUALI PLAYER-SHOP
    }
}
