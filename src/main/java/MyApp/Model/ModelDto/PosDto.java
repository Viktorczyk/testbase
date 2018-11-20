package MyApp.Model.ModelDto;

import MyApp.Model.BaseModel;
import MyApp.Model.Headers;
import MyApp.Model.Position;
import groovy.transform.builder.Builder;
import lombok.Data;

import java.sql.Date;


@Data
public class PosDto  {
    private Integer aaid;

    private Integer lp;
    private String indeks;
    private String description;


    private Date dataModified;
    private Integer quantity;


    private Integer headers;

}
