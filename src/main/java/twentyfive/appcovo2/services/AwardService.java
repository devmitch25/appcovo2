package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.Award;
import twentyfive.appcovo2.repositories.AwardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AwardService {

    @Autowired
    private AwardRepository awardRepository;

    public boolean existsAward(Long id) { return awardRepository.existsById(id); }

    public Optional<Award> getById(Long id) {
        return awardRepository.findById(id);
    }

    public List<Award> getAll(){
        return awardRepository.findAll();
    }

    public Award save(Award award) {
        return awardRepository.save(award);
    }

    public Award update(Long id, Award award) {
        //TODO
        return new Award();
    }

    public Award patch(Long id, Award award) {
        //TODO
        return new Award();
    }

    public void delete(Long id) {
        if(!awardRepository.existsById(id)) {
            throw new IllegalArgumentException("Award con ID: "+id+" non trovato");
        }
        awardRepository.deleteById(id);
        //TODO QUANDO VIENE CANCELLATO, VA ELIMINATO ANCHE DAL RESTO DELLE TABELLE RELAZIONALI
        //TODO TIPO PLAYER-AWARD?? PERÒ RIMANE IL FATTO CHE IL PREMIO è STATO RISCATTATO VERAMENTE
        //TODO NON DOVREBBE ESSERE TOLTO DALLA "CRONOLOGIA"
    }
}
