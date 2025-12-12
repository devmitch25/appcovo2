package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.models.Format;

import java.util.Optional;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long> {

    Optional<Format> findByNameAndGame_Id(String name, Long gameId);
}
