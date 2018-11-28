package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Location extends BaseModel implements Serializable {

    private String location;
    private String description;
    private Integer shelf;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "location_id")
    private Position position;

}
