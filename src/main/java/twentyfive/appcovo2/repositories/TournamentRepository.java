package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.enums.TournamentStatus;
import twentyfive.appcovo2.models.Tournament;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findAllByShop_Id(Long shopId);

    List<Tournament> findAllByShop_City(String shopCity);

    List<Tournament> findAllByStatus(TournamentStatus status);

    List<Tournament> findAllByStatusAndShop_Id(TournamentStatus status, Long shopId);

    //List<Tournament> findAllByStatusAndShop_IdAndDate_YearAndDate_MonthAndDate_Day(TournamentStatus status, Long shopId, Integer dateYear, Integer dateMonth, Integer dateDay);
    @Query("SELECT t FROM Tournament t " +
            "WHERE t.status = :status " +
            "AND t.shop.id = :shopId " +
            "AND FUNCTION('YEAR', t.date) = :year " +
            "AND FUNCTION('MONTH', t.date) = :month " +
            "AND FUNCTION('DAY', t.date) = :day")
    List<Tournament> findByStatusAndShopIdAndDateComponents(
            @Param("status") TournamentStatus status,
            @Param("shopId") Long shopId,
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day
    );
}
