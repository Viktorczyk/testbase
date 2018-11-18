package MyApp.Repository;

import MyApp.Model.Headers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Headers, Integer> {

    Headers getHeadersById (Integer headerId);



}
