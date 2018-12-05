package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Items extends BaseModel implements Serializable {

    private String number;
    private String name;
    private String description;
    private String jm;
    private double weight;


    @OneToMany(mappedBy = "items", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Position> positions ;

}
