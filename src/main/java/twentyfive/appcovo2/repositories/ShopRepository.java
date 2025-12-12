package twentyfive.appcovo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twentyfive.appcovo2.models.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
