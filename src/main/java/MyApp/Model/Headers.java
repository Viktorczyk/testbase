package MyApp.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity(name = "header")
public class Headers extends BaseModel{

    private String number;
    private String typHeader;
    private Date data;
    private String description;


    @OneToMany(mappedBy = "headers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Position> positions ;




}