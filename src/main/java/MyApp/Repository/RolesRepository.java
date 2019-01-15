package MyApp.Repository;

import MyApp.Model.User.Roles;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByRoles(String roles);
}
