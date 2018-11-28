package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name ="positions")
public class Position extends BaseModel implements Serializable {

    private Integer lp;
    private String description;

    @CreationTimestamp
    private Date dataModified;
    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @JsonIgnore
     private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="header_id" )
    @JsonIgnore
    private Headers headers;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="item_id" )
    @JsonIgnore
    private Items items;


}
