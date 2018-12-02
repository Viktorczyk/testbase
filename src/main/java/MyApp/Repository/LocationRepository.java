package MyApp.Repository;

import MyApp.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Integer> {

   @Query("Select v from Location v where v.warehouse.id = :wareId")
   //@Query("Select v from location v inner join warehouse w on v.warehouse_id= w.id where w.id = :wareId")
    List<Location> findByWarehouse(@Param("wareId") Integer wareId);


}
