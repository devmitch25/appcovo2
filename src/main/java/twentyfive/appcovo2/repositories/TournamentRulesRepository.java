package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.models.TournamentRules;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRulesRepository extends JpaRepository<TournamentRules, Long> {
    Optional<TournamentRules> findByName(String name);

    List<TournamentRules> findAllByFormat_Id(Long formatId);
}
