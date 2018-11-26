package MyApp.Model.ModelDto;

import MyApp.Model.BaseModel;
import MyApp.Model.Headers;
import MyApp.Model.Position;
import groovy.transform.builder.Builder;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;


@Data
public class PosDto  {


    private Integer lp;
    private String indeks;
    private String description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataModified;
    private Integer quantity;


    private Integer headers;
    private Integer items;

}
