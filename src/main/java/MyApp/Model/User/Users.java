package MyApp.Model.User;

import MyApp.Model.BaseModel;
import MyApp.Model.User.Roles;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Users extends BaseModel {

    private String name;

    private String surname;

    private String password;

    private String passwordConfirm;

    private String email;

    private Boolean active;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name= "user_roles",
                joinColumns = @JoinColumn(name="user_id"),
                inverseJoinColumns =  @JoinColumn(name="role_id"))
    private Set<Roles> roles;

}
