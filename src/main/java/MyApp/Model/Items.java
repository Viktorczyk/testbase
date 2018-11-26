package MyApp.Model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Items extends BaseModel implements Serializable {


    private String indeks;
    private String description;
    private double weight;

    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Position> positions ;


}
