package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.enums.TournamentStatus;
import twentyfive.appcovo2.models.Tournament;
import twentyfive.appcovo2.repositories.TournamentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Optional<Tournament> getById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> getAll(){
        return tournamentRepository.findAll();
    }

    public List<Tournament> getAllByShopId(Long shopId) {
        return tournamentRepository.findAllByShop_Id(shopId);
    }
    public List<Tournament> getAllByShopCity(String shopCity) {
        return tournamentRepository.findAllByShop_City(shopCity);
    }

    public List<Tournament> getAllByStatus(TournamentStatus status) {
        return tournamentRepository.findAllByStatus(status);
    }
    public List<Tournament> getAllByStatusAndShopID(TournamentStatus status, Long shopId) {
        return tournamentRepository.findAllByStatusAndShop_Id(status, shopId);
        //TODO PRIMA STATUS, POI SHOP PERCHÉ COSÌ RICERCA PIÙ EFFICACE
    }

    public List<Tournament> getAllByStatusAndShopIdAndDate(TournamentStatus status, Long shopId, Date date) {
        //TODO INTEGER O INT?
        Integer year = Integer.valueOf(date.getYear());
        Integer month = Integer.valueOf(date.getMonth());
        Integer day = Integer.valueOf(date.getDay());
        return tournamentRepository.findByStatusAndShopIdAndDateComponents(status, shopId, year, month, day);
    }

    //TODO VANNO FATTI TUTTI I VARI METODI DI GETALL CON I VARI FILTRI?


    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament update(Long id, Tournament tournament) {
        //TODO
        return new Tournament();
    }

    public Tournament patch(Long id, Tournament tournament) {
        //TODO
        return new Tournament();
    }

    public void delete(Long id) {
        if(!tournamentRepository.existsById(id)) {
            throw new IllegalArgumentException("Tournament con ID: "+id+" non trovato");
        }
        tournamentRepository.deleteById(id);
    }
}
