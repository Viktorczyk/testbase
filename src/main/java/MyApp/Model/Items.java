package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Items extends BaseModel implements Serializable {

    private String number;
    private String name;
    private String description;
    private String jm;
    private double weight;


    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Position> positions ;

}
