package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.TournamentRules;
import twentyfive.appcovo2.repositories.TournamentRulesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentRulesService {

    @Autowired
    private TournamentRulesRepository tournamentRulesRepository;

    public Optional<TournamentRules> getById(Long id) {
        return tournamentRulesRepository.findById(id);
    }

    public Optional<TournamentRules> getByName(String name) {
        return tournamentRulesRepository.findByName(name);
    }

    public List<TournamentRules> getAll(){
        return tournamentRulesRepository.findAll();
    }

    public List<TournamentRules> getAllByFormat(Long formatId){
        return tournamentRulesRepository.findAllByFormat_Id(formatId);
        //TODO è il gioco o il formato che ha il proprio regolamento? mmmmmm
    }

    public TournamentRules save(TournamentRules tournamentRules) {
        return tournamentRulesRepository.save(tournamentRules);
    }

    public TournamentRules update(Long id, TournamentRules tournamentRules) {
        //TODO
        return new TournamentRules();
    }

    public TournamentRules patch(Long id, TournamentRules tournamentRules) {
        //TODO
        return new TournamentRules();
    }

    public void delete(Long id) {
        if(!tournamentRulesRepository.existsById(id)) {
            throw new IllegalArgumentException("TournamentRules con ID: "+id+" non trovato");
        }
        tournamentRulesRepository.deleteById(id);
    }

}
