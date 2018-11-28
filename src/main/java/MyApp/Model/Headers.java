package MyApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity(name = "header")
public class Headers extends BaseModel implements Serializable {

    private String number;
    private TypeHeader typHeader;

    @CreationTimestamp
    private Date data;
    private String description;


    @OneToMany(mappedBy = "headers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Position> positions ;



}
