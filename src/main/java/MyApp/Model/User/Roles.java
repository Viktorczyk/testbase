package MyApp.Model.User;

import MyApp.Model.BaseModel;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Roles extends BaseModel {

    private String roles;

}
