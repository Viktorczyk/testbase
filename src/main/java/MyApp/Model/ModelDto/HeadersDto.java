package MyApp.Model.ModelDto;

import MyApp.Model.Position;
import MyApp.Model.TypeHeader;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class HeadersDto {

    private String number;
    private Integer typHeader;

    @CreationTimestamp
    private Date data;
    private String description;


    @OneToMany(mappedBy = "headers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Position> positions ;


}
