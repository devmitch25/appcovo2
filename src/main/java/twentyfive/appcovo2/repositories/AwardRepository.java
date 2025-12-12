package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.models.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

}
