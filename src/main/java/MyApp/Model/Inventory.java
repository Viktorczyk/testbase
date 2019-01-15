package MyApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory extends BaseModel implements Serializable {

    private String item;

    private Integer quantity;

    private String externalBatch;

    private String warehouse;

}
