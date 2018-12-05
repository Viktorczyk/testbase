package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "header")
public class Headers extends BaseModel implements Serializable {

    private String number;
    private TypeHeader typHeader;

    @CreationTimestamp
    private Date data;
    private String description;


    @OneToMany(mappedBy = "headers", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Position> positions ;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name ="warehouse_id" )
    @JsonIgnore
    private Warehouse warehouse;


}
