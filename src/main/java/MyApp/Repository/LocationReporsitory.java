package MyApp.Repository;

import MyApp.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationReporsitory extends JpaRepository<Location, Integer> {
}
