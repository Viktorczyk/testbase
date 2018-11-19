package MyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@Entity(name ="positions")
public class Position extends BaseModel {

    private Integer lp;
    private String indeks;
    private String description;
    private Date dataModified;
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="header_id" )
    @JsonIgnore
    private Headers headers;



}
