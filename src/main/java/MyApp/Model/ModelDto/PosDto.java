package MyApp.Model.ModelDto;

import MyApp.Model.BaseModel;
import MyApp.Model.Headers;
import MyApp.Model.Position;
import groovy.transform.builder.Builder;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;


@Data
public class PosDto  {


    private Integer lp;
    private String description;


    private Date dataModified;
    private Integer quantity;

    //nagłówek pozycji
    private Integer headers;

    //dodanie itema
    private Integer item;

    //lokalizacja
    private Integer location;



}
