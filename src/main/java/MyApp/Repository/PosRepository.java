package MyApp.Repository;

import MyApp.Model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosRepository extends JpaRepository<Position, Integer> {

    Position getPosByLp(Integer lp);

}
